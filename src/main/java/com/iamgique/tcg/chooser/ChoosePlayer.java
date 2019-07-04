package com.iamgique.tcg.chooser;

import com.iamgique.tcg.model.Card;
import com.iamgique.tcg.model.Select;

import java.io.IOException;
import java.util.List;

public interface ChoosePlayer {
    Select playerSelect(int health, int mana, List<Card> cardInHand, int opponentHealth);
}
