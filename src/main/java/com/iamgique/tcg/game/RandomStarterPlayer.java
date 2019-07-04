package com.iamgique.tcg.game;

import java.util.Random;

public class RandomStarterPlayer {
    public Player randomStartPlayer(Player player1, Player player2) {
        Random random = new Random();
        return random.nextBoolean() ? player1 : player2;
    }
}
