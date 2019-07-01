package com.iamgique.tcg.game;

import java.util.Random;

public class RandomStarterPlayer {
    private Random random = new Random();

    public Player choosePlayer(Player player1, Player player2) {
        return random.nextBoolean() ? player1 : player2;
    }
}
