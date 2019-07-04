package com.iamgique.tcg.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GameTest {
    @Mock
    Player player;

    @Mock
    Player opponent;

    @Mock
    RandomStarterPlayer randomStarterPlayer;

    Game game;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(randomStarterPlayer.randomStartPlayer(player, opponent)).thenReturn(player);
        when(player.getName()).thenReturn("HUMAN");
        when(opponent.getName()).thenReturn("COMPUTER");

        game = new Game(player, opponent, randomStarterPlayer);
    }

    @Test
    public void testStartAndGetWinnerIsHUMAN() {
        when(player.getHealth()).thenReturn(30);
        String resp = game.start();
        assertEquals("The winner is: HUMAN", resp);
    }

    @Test
    public void testStartAndGetWinnerIsCOMPUTER() {
        when(opponent.getHealth()).thenReturn(30);
        String resp = game.start();
        assertEquals("The winner is: COMPUTER", resp);
    }

    @Test
    public void testGetStarterPlayerIsCOMPUTERAndWinnerIsHUMAN() {
        when(randomStarterPlayer.randomStartPlayer(player, opponent)).thenReturn(opponent);

        when(player.getName()).thenReturn("HUMAN");
        when(opponent.getName()).thenReturn("COMPUTER");
        game = new Game(player, opponent, randomStarterPlayer);

        when(player.getHealth()).thenReturn(30);
        String resp = game.start();
        assertEquals("The winner is: HUMAN", resp);
    }

    @Test
    public void testStartGameWithOneRoundAndWinnerIsHUMAN() {
        when(player.getHealth()).thenReturn(15);
        when(opponent.getHealth()).thenReturn(5).thenReturn(5).thenReturn(0);

        when(player.checkPlayAble()).thenReturn(true).thenReturn(false);
        doNothing().when(player).play(opponent);

        game = new Game(player, opponent, randomStarterPlayer);
        String resp = game.start();

        verify(player, times(2)).checkPlayAble();
        verify(player, times(1)).play(opponent);

        assertEquals("The winner is: HUMAN", resp);
    }
}
