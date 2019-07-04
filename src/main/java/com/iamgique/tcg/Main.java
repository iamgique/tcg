package com.iamgique.tcg;

import com.iamgique.tcg.game.Player;
import com.iamgique.tcg.chooser.ComputerPlayer;
import com.iamgique.tcg.chooser.ConsolePlayer;
import com.iamgique.tcg.game.Game;
import com.iamgique.tcg.game.RandomStarterPlayer;

import java.util.Random;

public class Main {
    public static void main(String... args) {
        new Game(
                new Player("GIQUE", new ConsolePlayer()),
                new Player("COMPUTER", new ComputerPlayer()),
                new RandomStarterPlayer(new Random()))
                .start();
    }

    /*public static void main(String... args) {
        //highestCard();
        List<Card> hand = new ArrayList<>();
        List<Card> deck = Card.list(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8);
        Random rand = new Random();

        Card card = deck.get(rand.nextInt(deck.size()));
        hand.add(card);
        card = deck.get(rand.nextInt(deck.size()));
        hand.add(card);
        card = deck.get(rand.nextInt(deck.size()));
        hand.add(card);
        card = deck.get(rand.nextInt(deck.size()));
        hand.add(card);
        System.err.println(hand);
        List<Card> selectCards = new ArrayList<>();

        Optional<Card> a = getHitCard(5, hand);
        System.err.println(a);
    }

    private static Optional<Card> getHitCard(int mana, List<Card> cardInHand){
        cardInHand.sort(Comparator.comparingDouble(Card::getValue).reversed());
        System.err.println(cardInHand);

        List<Card> selectCards = new ArrayList<>();
        if(!cardInHand.isEmpty()){
            getCombo(mana, cardInHand, selectCards);
            System.err.println(selectCards);
        }

        return selectCards.stream().max(Comparator.comparing(Card::getValue));
    }

    private static void getCombo(int mana, List<Card> cardInHand, List<Card> selectCards){
        for(Card card : cardInHand){
            List<Card> temp = new ArrayList<>(cardInHand);
            if(selectCards.stream().mapToInt(Card::getValue).sum() + card.getValue() <= mana){
                selectCards.add(card);
                temp.remove(card);
                getCombo(mana - card.getValue(), temp, selectCards);
            }
        }
    }*/

    /*protected static Optional<Card> highestCard() {
        List<Card> hand = new ArrayList<>();
        List<Card> deck = Card.list(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8);
        Random rand = new Random();

        Card card = deck.get(rand.nextInt(deck.size()));
        hand.add(card);
        card = deck.get(rand.nextInt(deck.size()));
        hand.add(card);
        card = deck.get(rand.nextInt(deck.size()));
        hand.add(card);
        card = deck.get(rand.nextInt(deck.size()));
        hand.add(card);

        System.err.println(hand);
        System.err.println(hand.stream().filter(c -> c.getValue() <= 5).max(Comparator.comparing(Card::getValue)));

        return hand.stream().filter(c -> c.getValue() <= 5).max(Comparator.comparing(Card::getValue));
    }*/
}
