package com.iamgique.tcg.chooser;


import com.iamgique.tcg.constants.Action;
import com.iamgique.tcg.constants.Constant;
import com.iamgique.tcg.model.Card;

import com.iamgique.tcg.model.Select;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ConsolePlayerTest {
    @InjectMocks
    ConsolePlayer consolePlayer;

    @Rule
    public final TextFromStandardInputStream consoleInput = emptyStandardInputStream();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        consolePlayer = new ConsolePlayer();
    }

    @Test
    public void testPlayerSelectHitWithInputIsFiveShouldBeReturnHitFive() {
        consoleInput.provideText("5");

        int health = Constant.MAX_HEALTH;
        int mana = Constant.MAX_MANA;
        int opponentHealth = 20;
        List<Card> cardInHand = Card.list(1, 2, 5, 3);

        Select resp = consolePlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(Action.HIT.toString(), resp.getAction().toString());
        assertEquals(5, resp.getCard().get().getValue());
    }

    @Test
    public void testPlayerSelectHealWithInputIsNineShouldBeReturnHealNine() {
        consoleInput.provideText("9h");

        int health = Constant.MAX_HEALTH;
        int mana = Constant.MAX_MANA;
        int opponentHealth = 20;
        List<Card> cardInHand = Card.list(1, 9, 5, 3);

        Select resp = consolePlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(Action.HEAL.toString(), resp.getAction().toString());
        assertEquals(9, resp.getCard().get().getValue());
    }

    @Test
    public void testSelectHitCardWithInputAreNineIsNotEnoughAndThenInputZeroIsNotInHandAndThenInputForeShouldEnough() {
        consoleInput.provideLines("9", "0", "4");

        int health = Constant.MAX_HEALTH;
        int mana = 4;
        int opponentHealth = 20;
        List<Card> cardInHand = Card.list(1, 9, 4, 3);

        Select resp = consolePlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(Action.HIT.toString(), resp.getAction().toString());
        assertEquals(4, resp.getCard().get().getValue());
    }

    @Test
    public void testIOException() {
        consoleInput.throwExceptionOnInputEnd(new IOException());

        int health = Constant.MAX_HEALTH;
        int mana = Constant.MAX_MANA;
        int opponentHealth = 20;
        List<Card> cardInHand = Card.list(1, 9, 5, 3);

        Select resp = consolePlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(null, resp.getAction());
        assertEquals(Optional.empty(), resp.getCard());
    }

}
