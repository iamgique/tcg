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
    private String getInput() throws IOException {
        InputStreamReader inputData = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputData);
        return br.readLine();
    }

    public Select playerSelect(int health, int mana, List<Card> cardInHand, int opponentHealth) {
        while (true) {
            try {
                String input = getInput();
                String choose = input;
                Action action = hitOrHeal(input);
                choose = action.equals(Action.HEAL) ? replaceInputForHeal(input) : choose;

                return getSelect(mana, cardInHand, input, choose, action);
            } catch (NumberFormatException e) {
                System.err.println("The input is wrong or mana not enough or empty card in hand your the input is: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error " + e.getMessage());
                e.printStackTrace();
                return new Select(Optional.empty(), null);
            }
        }
    }

    private Select getSelect(int mana, List<Card> cardInHand, String input, String choose, Action action) {
        if (Integer.parseInt(choose) <= mana && cardInHand.contains(new Card(Integer.parseInt(choose)))) {
            return new Select(Optional.of(new Card(Integer.parseInt(choose))), action);
        } else {
            throw new NumberFormatException(input);
        }
    }

    private Action hitOrHeal(String input) {
        return input.toUpperCase().endsWith("H") ? Action.HEAL : Action.HIT;
    }

    private String replaceInputForHeal(String input) {
        return input.toUpperCase().replace("H", "");
    }
}
