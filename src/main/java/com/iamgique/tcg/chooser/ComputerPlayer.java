package com.iamgique.tcg.chooser;

import com.iamgique.tcg.constants.Action;
import com.iamgique.tcg.model.Card;
import com.iamgique.tcg.model.Select;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ComputerPlayer implements ChoosePlayer {
    public Select playerSelect(int health, int mana, List<Card> cardInHand, int opponentHealth){
        if(killer(mana, cardInHand, opponentHealth) || health >= 10){
            return new Select(getHitCard(mana, cardInHand), Action.HIT);
        } else {
            return new Select(getHighestCard(mana, cardInHand), Action.HEAL);
        }
    }

    public Optional<Card> getHighestCard(int mana, List<Card> cardInHand) {
        return cardInHand.stream().filter(c -> c.getValue() <= mana).max(Comparator.comparing(Card::getValue));
    }

    private boolean killer(int mana, List<Card> cardInHand, int opponentHealth){
        List<Card> selectCards = new ArrayList<>();
        getCombo(mana, cardInHand, selectCards);
        return selectCards.stream().mapToInt(Card::getValue).sum() >= opponentHealth ? true : false;
    }

    private static Optional<Card> getHitCard(int mana, List<Card> cardInHand){
        cardInHand.sort(Comparator.comparingDouble(Card::getValue).reversed());

        List<Card> selectCards = new ArrayList<>();
        getCombo(mana, cardInHand, selectCards);
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
    }
}
