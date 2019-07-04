package com.iamgique.tcg.game;

import com.iamgique.tcg.constants.Action;
import com.iamgique.tcg.constants.Constant;
import com.iamgique.tcg.exception.ResponseException;
import com.iamgique.tcg.model.Card;
import com.iamgique.tcg.model.Select;
import com.iamgique.tcg.chooser.ChoosePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Player {
    private final String name;

    private int health = Constant.MAX_HEALTH;
    private int mana = 0;
    private int manaSlots = 0;
    private ChoosePlayer choosePlayer;

    private List<Card> deck = Card.list(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8);
    private List<Card> hand = new ArrayList<>();

    Random rand = new Random();

    public Player(String name, ChoosePlayer choosePlayer) {
        this.name = name;
        this.choosePlayer = choosePlayer;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getManaSlots() {
        return manaSlots;
    }

    public void drawStartCard() {
        for (int i = 0; i < Constant.START_CARD_SIZE; i++) {
            drawCard();
        }
    }

    public void drawCard() {
        if (!deck.isEmpty()) {
            Card card = deck.get(rand.nextInt(deck.size()));
            deck.remove(card);
            addOrDropCard(card);
        } else {
            health--;
            System.out.println(this.getName() + " is bleeding out a health: " + health + "/" + Constant.MAX_HEALTH + " left");
        }
    }

    private void addOrDropCard(Card card) {
        if (hand.size() < Constant.MAX_CARD_SIZE) {
            hand.add(card);
            System.out.println(this + " draws card: " + card);
        } else {
            System.out.println(this.getName() + " The card is overloading then drop the card " + card.getValue());
        }
    }

    public void addMana() {
        manaSlots = manaSlots < Constant.MAX_MANA ? manaSlots + 1 : manaSlots;
        mana = manaSlots;
    }

    public void addHeal(int amount) {
        health = Math.min(health + amount, Constant.MAX_HEALTH);
    }

    public void hit(int damage){
        health -= damage;
    }

    public boolean checkPlayAble() {
        return hand.stream().filter(c -> c.getValue() <= mana).count() >= 1;
    }

    public void play(Player opponent) {
        Select selected = choosePlayer.playerSelect(health, mana, hand, opponent.getHealth());
        Optional<Card> card = selected.getCard();
        if (card.isPresent()) {
            action(card.get(), opponent, selected.getAction());
        } else {
            throw new ResponseException("No card can be played");
        }
    }

    public void action(Card card, Player opponent, Action action) {
        System.out.println(this.getName() + ": action: " + action + " amount: " + card);
        mana -= card.getValue();
        hand.remove(card);
        if(action.equals(Action.HEAL)){
            addHeal(card.getValue());
        } else {
            opponent.hit(card.getValue());
        }
    }

    @Override
    public String toString() {
        return name +
                " Health: [" + health + "/" + Constant.MAX_HEALTH + "]" +
                " Mana: [" + mana + "/" + manaSlots + "]" +
                " In hand: " + hand +
                " Deck size: [" + deck.size() + "]" +
                " Deck list: " + deck;
    }
}
