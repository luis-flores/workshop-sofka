package com.example.demo;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DojoStreamTest {

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35(){
        List<Player> list = CsvUtilFile.getPlayers();
        Set<Player> result = list.stream()
            .filter(jugador -> jugador.getAge() > 35)
            .collect(Collectors.toSet());
        result.forEach(System.out::println);
    }

    @Test
    void jugadoresMayoresA35SegunClub(){
        List<Player> list = CsvUtilFile.getPlayers();
        Map<String, List<Player>> result = list.stream()
            .filter(player -> player.getAge() > 35)
            .distinct()
            .collect(Collectors.groupingBy(Player::getClub));

        result.forEach((key, jugadores) -> {
            System.out.println("\n");
            System.out.println(key + ": ");
            jugadores.forEach(System.out::println);
        });
    }

    @Test
    void mejorJugadorConNacionalidadFrancia(){
        List<Player> list = CsvUtilFile.getPlayers();
        Player mejorJugador = list.stream()
            .filter(player -> player.getNational().equals("France"))
            .max(new BestPlayerComparator())
            .get();

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
          .max(new BestPlayerComparator())
          .get()
          .getClub();

        System.out.println(club);
    }

    @Test
    void elMejorJugador(){
        List<Player> list = CsvUtilFile.getPlayers();
        Player mejorJugador = list.stream()
           .max(new BestPlayerComparator())
           .get();

        System.out.println(mejorJugador);
    }

    @Test
    void mejorJugadorSegunNacionalidad(){
        List<Player> list = CsvUtilFile.getPlayers();
        Map<String, String> mejorJugadorSegunNacionalidad = list.stream()
            .collect(Collectors.groupingBy(
                Player::getNational,
                Collectors.collectingAndThen(
                    Collectors.maxBy(new BestPlayerComparator()),
                    player -> player.map(p -> p.getName()).orElse(null)
                )
            ));

        mejorJugadorSegunNacionalidad.forEach((nacionalidad, mejorJugador) ->
            System.out.println("Mejor jugador de " + nacionalidad + ": " + mejorJugador));
    }

}
