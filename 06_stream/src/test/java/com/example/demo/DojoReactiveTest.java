package com.example.demo;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class DojoReactiveTest {

    private final Comparator<Player> comparadorMejorJugador = (Player p1, Player p2) -> {
        if (p1.getWinners() > p2.getWinners())
            return 1;
        if (p1.getWinners() < p2.getWinners())
            return -1;

        return Integer.compare(p1.getGames(), p2.getGames());
    };

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35() {
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable.filter(jugador -> jugador.getAge() > 35)
            .subscribe(System.out::println);
    }


    @Test
    void jugadoresMayoresA35SegunClub(){
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable.filter(player -> player.getAge() > 35)
            .distinct()
            .groupBy(Player::getClub)
            .flatMap(groupedFlux -> groupedFlux
                .collectList()
                .map(players -> {
                    Map<String, List<Player>> map = new HashMap<>();
                    map.put(groupedFlux.key(), players);
                    return map;
                }))
            .subscribe(map -> {
                map.forEach((key, value) -> {
                    System.out.println(key + ": ");
                    value.forEach(v ->
                        System.out.println(v.getName())
                    );
                });
            });
    }

    // ********************************

    @Test
    void mejorJugadorConNacionalidadFrancia(){
        List<Player> list = CsvUtilFile.getPlayers();
        Mono<Player> observable = Flux.fromIterable(list)
            .filter(player -> player.getNational().equals("France"))
            .reduce((p1, p2) -> comparadorMejorJugador.compare(p1, p2) >= 0 ? p1 : p2);

        observable.subscribe(System.out::println);
    }

    @Test
    void clubsAgrupadosPorNacionalidad(){
        List<Player> list = CsvUtilFile.getPlayers();

        Flux.fromIterable(list)
            .groupBy(Player::getNational)
            .subscribe(groupedFlux -> {
                System.out.println();
                String nacionalidad = groupedFlux.key();
                System.out.println(nacionalidad + ":");

                groupedFlux.subscribe(player -> {
                   System.out.println(player.getClub());
                });
            });
    }

    @Test
    void clubConElMejorJugador(){
        List<Player> list = CsvUtilFile.getPlayers();
        Mono<Player> observable = Flux.fromIterable(list)
            .reduce((p1, p2) -> comparadorMejorJugador.compare(p1, p2) >= 0 ? p1 : p2);

        observable.subscribe(player -> System.out.println(player.getClub()));
    }

    @Test
    void clubConElMejorJugador2() {
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list)
            .reduce((p1, p2) -> comparadorMejorJugador.compare(p1, p2) >= 0 ? p1 : p2)
            .flux();

        observable.subscribe(player -> System.out.println(player.getClub()));
    }

    @Test
    void ElMejorJugador() {
        List<Player> list = CsvUtilFile.getPlayers();
        Mono<Player> observable = Flux.fromIterable(list)
            .reduce((p1, p2) -> comparadorMejorJugador.compare(p1, p2) >= 0 ? p1 : p2);

        observable.subscribe(player -> System.out.println(player.getName()));
    }

    @Test
    void ElMejorJugador2() {
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list)
            .reduce((p1, p2) -> comparadorMejorJugador.compare(p1, p2) >= 0 ? p1 : p2)
            .flux();

        observable.subscribe(player -> System.out.println(player.getName()));
    }

    @Test
    void mejorJugadorSegunNacionalidad(){
        List<Player> list = CsvUtilFile.getPlayers();
        Flux.fromIterable(list)
            .groupBy(Player::getNational)
            .flatMap(group -> group.reduce((p1, p2) -> comparadorMejorJugador.compare(p1, p2) >= 0 ? p1 : p2))
            .subscribe(mejorJugadorSegunNacionalidad -> {
                String nacionalidad = mejorJugadorSegunNacionalidad.getNational();
                System.out.println(nacionalidad + ": " + mejorJugadorSegunNacionalidad.getName());
            });
    }
}
