package com.iamgique.tcg.chooser;

import com.iamgique.tcg.constants.Action;
import com.iamgique.tcg.model.Card;
import com.iamgique.tcg.model.Select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

public class ConsolePlayer implements ChoosePlayer {

    public Select playerSelect(int health, int mana, List<Card> cardInHand, int opponentHealth) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));

        while (true) {
            try {
                String input = br.readLine();
                String choose = input;
                Action action = hitOrHeal(input);

                if (action.equals(Action.HEAL)) {
                    choose = input.toUpperCase().replace("H", "");
                }

                if(Integer.parseInt(choose) <= mana && cardInHand.contains(new Card(Integer.parseInt(choose)))) {
                    return new Select(Optional.of(new Card(Integer.parseInt(choose))), action);
                } else {
                    throw new NumberFormatException(input);
                }
            } catch (NumberFormatException e) {
                System.err.println("The input is wrong or mana not enough or empty card in hand your the input is: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private Action hitOrHeal(String input) {
        return input.toUpperCase().endsWith("H") ? Action.HEAL : Action.HIT;
    }


}
