package com.iamgique.tcg.game;

import com.iamgique.tcg.exception.ResponseException;

import java.util.Optional;

public class Game {

    private Player activePlayer;
    private Player opponentPlayer;

    public Game(Player player1, Player player2, RandomStarterPlayer randomStarterPlayer) {
        activePlayer = randomStarterPlayer.randomStartPlayer(player1, player2);
        opponentPlayer = activePlayer == player1 ? player2 : player1;

        System.out.println("Random Card");
        activePlayer.drawStartCard();
        opponentPlayer.drawStartCard();

        System.out.println("Let's Play");
        opponentPlayer.drawCard();
    }

    public void start() {
        while(true) {
            try {
                checkWinner();
                beginTurn();
                while (activePlayer.checkPlayAble()) {
                    activePlayer.play(opponentPlayer);
                }
                checkWinner();
                endTurn();
            } catch (ResponseException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public void beginTurn() {
        System.out.println(activePlayer.getName() + ": Begin turn...");
        activePlayer.addMana();
        activePlayer.drawCard();
    }

    public void endTurn() {
        System.out.println(activePlayer.getName() + ": End turn");
        Player temp = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = temp;
    }

    private void checkWinner() {
        Optional.ofNullable(getWinner()).ifPresent(s -> {
            throw new ResponseException("The winner is: " + getWinner());
        });
    }

    private String getWinner(){
        if (activePlayer.getHealth() < 1) {
            return opponentPlayer.getName();
        } else if (opponentPlayer.getHealth() < 1) {
            return activePlayer.getName();
        } else {
            return null;
        }
    }

}
