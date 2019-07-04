package com.iamgique.tcg.game;

import com.iamgique.tcg.exception.ResponseException;

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

    public String start() {
        while(true) {
            try {
                getWinner();
                beginTurn();
                while (activePlayer.checkPlayAble()) {
                    activePlayer.play(opponentPlayer);
                }
                getWinner();
                endTurn();
            } catch (ResponseException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        }
    }

    private void beginTurn() {
        System.out.println(activePlayer.getName() + ": Begin turn...");
        activePlayer.addMana();
        activePlayer.drawCard();
    }

    private void endTurn() {
        System.out.println(activePlayer.getName() + ": End turn");
        Player temp = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = temp;
    }

    private String getWinner(){
        if (activePlayer.getHealth() < 1) {
            throw new ResponseException("The winner is: " + opponentPlayer.getName());
        } else if (opponentPlayer.getHealth() < 1) {
            throw new ResponseException("The winner is: " + activePlayer.getName());
        } else {
            return null;
        }
    }

}
