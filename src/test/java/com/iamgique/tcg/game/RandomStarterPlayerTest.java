package com.iamgique.tcg.game;

import com.iamgique.tcg.chooser.ComputerPlayer;
import com.iamgique.tcg.chooser.ConsolePlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RandomStarterPlayerTest {
    @Mock
    Random random;

    @InjectMocks
    RandomStarterPlayer randomStarterPlayer;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        randomStarterPlayer = new RandomStarterPlayer(random);
    }

    @Test
    public void testRandomShouldReturnPlayer() {
        Player p1 = new Player("PLAYER", new ConsolePlayer());
        Player p2 = new Player("COMPUTER", new ComputerPlayer());

        when(random.nextBoolean()).thenReturn(false);
        Player resp = randomStarterPlayer.randomStartPlayer(p1, p2);

        assertEquals("COMPUTER", resp.getName());
    }

    @Test
    public void testRandomShouldReturnComputer() {
        Player p1 = new Player("PLAYER", new ConsolePlayer());
        Player p2 = new Player("COMPUTER", new ComputerPlayer());

        when(random.nextBoolean()).thenReturn(true);
        Player resp = randomStarterPlayer.randomStartPlayer(p1, p2);

        assertEquals("PLAYER", resp.getName());
    }
}
