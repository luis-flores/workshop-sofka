package com.example.demo;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DojoStreamTest {

    private final Comparator<Player> comparadorMejorJugador = (Player p1, Player p2) -> {
        if (p1.getWinners() > p2.getWinners())
            return 1;
        if (p1.getWinners() < p2.getWinners())
            return -1;

        if (p1.getGames() > p2.getGames())
            return 1;
        if (p1.getGames() < p2.getGames())
            return -1;

        return 0;
    };

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35(){
        List<Player> list = CsvUtilFile.getPlayers();
        List<String> jugadoresMayoresA35 = list.stream()
            .filter(jugador -> jugador.getAge() > 35)
            .map(Player::getName)
            .collect(Collectors.toList());

        jugadoresMayoresA35.forEach(System.out::println);
    }

    @Test
    void jugadoresMayoresA35SegunClub(){
        List<Player> list = CsvUtilFile.getPlayers();
        Map<String, List<String>> result = list.stream()
            .filter(player -> player.getAge() > 35)
            .collect(Collectors.groupingBy(
                Player::getClub,
                Collectors.mapping(Player::getName, Collectors.toList())
            ));

        result.forEach((key, jugadores) -> {
            System.out.println((!key.isEmpty() ? key : "Without club") + ": ");
            jugadores.forEach(System.out::println);
            System.out.println();
        });
    }

    @Test
    void mejorJugadorConNacionalidadFrancia(){
        List<Player> list = CsvUtilFile.getPlayers();
        String mejorJugador = list.stream()
            .filter(player -> player.getNational().equals("France"))
            .max(comparadorMejorJugador)
            .get()
            .getName();

        System.out.println(mejorJugador);
    }

    @Test
    void clubesAgrupadosPorNacionalidad(){
        List<Player> list = CsvUtilFile.getPlayers();
        Map<String, List<String>> clubesPorNacionalidad = list.stream()
            .collect(Collectors.groupingBy(
                Player::getNational,
                Collectors.mapping(Player::getClub, Collectors.toList())
            ));

        clubesPorNacionalidad.forEach((nacionalidad, clubes) -> {
            System.out.println("Nacionalidad: " + nacionalidad + ", Clubes: " + clubes);
        });
    }

    @Test
    void clubConElMejorJugador(){
        List<Player> list = CsvUtilFile.getPlayers();
        String club = list.stream()
          .max(comparadorMejorJugador)
          .get()
          .getClub();

        System.out.println(club);
    }

    @Test
    void elMejorJugador(){
        List<Player> list = CsvUtilFile.getPlayers();
        String mejorJugador = list.stream()
           .max(comparadorMejorJugador)
           .get()
           .getName();

        System.out.println(mejorJugador);
    }

    @Test
    void mejorJugadorSegunNacionalidad(){
        List<Player> list = CsvUtilFile.getPlayers();
        Map<String, String> mejorJugadorSegunNacionalidad = list.stream()
            .collect(Collectors.groupingBy(
                Player::getNational,
                Collectors.collectingAndThen(
                    Collectors.maxBy(comparadorMejorJugador),
                    player -> player.map(p -> p.getName()).orElse(null)
                )
            ));

        mejorJugadorSegunNacionalidad.forEach((nacionalidad, mejorJugador) ->
            System.out.println("Mejor jugador de " + nacionalidad + ": " + mejorJugador));
    }

}
