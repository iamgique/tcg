package com.iamgique.tcg.game;

import com.iamgique.tcg.chooser.ChoosePlayer;
import com.iamgique.tcg.constants.Action;
import com.iamgique.tcg.constants.Constant;
import com.iamgique.tcg.exception.ResponseException;
import com.iamgique.tcg.model.Card;
import com.iamgique.tcg.model.Select;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class PlayerTest {
    @InjectMocks
    Player player;

    @InjectMocks
    Player opponent;

    @Mock
    ChoosePlayer choosePlayer;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        player = new Player("GIQUE", choosePlayer);
        opponent = new Player("COMPUTER", choosePlayer);
    }

    @Test
    public void testGetName() {
        assertEquals("GIQUE", player.getName());
    }

    @Test
    public void testGetInitHealth() {
        assertEquals(30, player.getHealth());
    }

    @Test
    public void testGetInitManaAndManaSlot() {
        assertEquals(0, player.getMana());
        assertEquals(0, player.getManaSlots());
    }

    @Test
    public void testAddManaMoreThanMaxManaSlot() {
        for(int i = 0; i < 11; i++) player.addMana();
        assertEquals(10, player.getMana());
        assertEquals(10, player.getManaSlots());
    }

    @Test
    public void testPlayAndHit() {
        player.addMana();
        when(choosePlayer.playerSelect(anyInt(), anyInt(), anyList(), anyInt()))
                .thenReturn(new Select(Optional.of(new Card(Integer.parseInt("1"))), Action.HIT));
        player.play(opponent);

        assertEquals(0, player.getMana());
        assertEquals(29, opponent.getHealth());
    }

    @Test
    public void testPlayAndHeal() {
        player.hit(5);
        player.addMana();
        player.addMana();

        when(choosePlayer.playerSelect(anyInt(), anyInt(), anyList(), anyInt()))
                .thenReturn(new Select(Optional.of(new Card(Integer.parseInt("2"))), Action.HEAL));
        player.play(opponent);

        assertEquals(27, player.getHealth());
    }

    @Test
    public void testPlayShouldThrowsResponseException() {
        when(choosePlayer.playerSelect(anyInt(), anyInt(), anyList(), anyInt()))
                .thenReturn(new Select(Optional.empty(), null));
        try {
            player.play(opponent);
        } catch (ResponseException e) {
            assertEquals("No card can be played", e.getMessage());
        }
    }

    @Test
    public void testCheckPlayAbleShouldReturnTrue() {
        for(int i = 0; i < 10; i++) player.addMana();
        player.drawStartCard();
        assertTrue(player.checkPlayAble());
    }

    @Test
    public void testCheckPlayAbleWithoutManaShouldReturnFalse() {
        player.drawStartCard();
        assertFalse(player.checkPlayAble());
    }

    @Test
    public void testDrawCardUntilDeckIsEmptyAndBleedingOut() {
        for(int i = 0; i < 20; i++) player.drawCard();
        player.drawCard();
        assertEquals(29, player.getHealth());
    }
}
