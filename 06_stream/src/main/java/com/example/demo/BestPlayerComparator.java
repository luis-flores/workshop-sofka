package com.example.demo;

import java.util.Comparator;

public class BestPlayerComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getWinners() > o2.getWinners()) {
            return 1;
        } else if (o1.getWinners() < o2.getWinners()) {
            return -1;
        }

        if (o1.getGames() > o2.getGames()) {
            return 1;
        } else if (o1.getGames() < o2.getGames()) {
            return -1;
        }

        return 0;
    }
}
