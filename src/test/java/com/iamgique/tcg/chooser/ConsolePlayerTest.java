package com.iamgique.tcg.chooser;


import com.iamgique.tcg.constants.Action;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;
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
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        consolePlayer = new ConsolePlayer();
    }

    private static List<Card> prepareCardInHand(Integer... values) {
        return stream(values).map(Card::new).collect(toCollection(ArrayList::new));
    }

    @Test
    public void testPlayerSelectHitWithInputIsFiveShouldBeReturnHitFive() throws Exception {
        consoleInput.provideText("5");

        int health = 30;
        int mana = 10;
        int opponentHealth = 20;
        List<Card> cardInHand = prepareCardInHand(1, 2, 5, 3);

        Select resp = consolePlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(Action.HIT.toString(), resp.getAction().toString());
        assertEquals(5, resp.getCard().get().getValue());
    }

    @Test
    public void testPlayerSelectHealWithInputIsNineShouldBeReturnHealNine() throws Exception {
        consoleInput.provideText("9h");

        int health = 30;
        int mana = 10;
        int opponentHealth = 20;
        List<Card> cardInHand = prepareCardInHand(1, 9, 5, 3);

        Select resp = consolePlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(Action.HEAL.toString(), resp.getAction().toString());
        assertEquals(9, resp.getCard().get().getValue());
    }

    @Test
    public void testSelectHitCardWithInputAreNineIsNotEnoughAndThenInputZeroIsNotInHandAndThenInputForeShouldEnough() throws Exception {
        consoleInput.provideLines("9", "0", "4");

        int health = 30;
        int mana = 4;
        int opponentHealth = 20;
        List<Card> cardInHand = prepareCardInHand(1, 9, 4, 3);

        Select resp = consolePlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(Action.HIT.toString(), resp.getAction().toString());
        assertEquals(4, resp.getCard().get().getValue());
    }

    @Test
    public void testIOException() {
        consoleInput.throwExceptionOnInputEnd(new IOException());

        int health = 30;
        int mana = 10;
        int opponentHealth = 20;
        List<Card> cardInHand = prepareCardInHand(1, 9, 5, 3);

        Select resp = consolePlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(null, resp.getAction());
        assertEquals(Optional.empty(), resp.getCard());
    }

}
