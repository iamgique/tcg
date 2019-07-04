package com.iamgique.tcg.game;

import java.util.Random;

public class RandomStarterPlayer {
    private Random random;

    public RandomStarterPlayer(Random random) {
        this.random = random;
    }

    public Player randomStartPlayer(Player player1, Player player2) {
        return random.nextBoolean() ? player1 : player2;
    }
}
