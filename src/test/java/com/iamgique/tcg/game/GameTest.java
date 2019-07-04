package com.iamgique.tcg.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class GameTest {
    @InjectMocks
    Game game;

    @Mock
    Player player;

    @Mock
    RandomStarterPlayer randomStarterPlayer;

    private Player activePlayer = player;
    private Player opponentPlayer = player;

    /*@Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        game = new Game(activePlayer, opponentPlayer, randomStarterPlayer);
        //when(randomStarterPlayer.randomStartPlayer(activePlayer, opponentPlayer)).thenReturn(activePlayer);
    }

    @Test
    public void test() throws Exception {
        //when(randomStarterPlayer.randomStartPlayer(any(), any())).thenReturn(player);
        //doNothing().when(player).drawStartCard();



    }*/
}
