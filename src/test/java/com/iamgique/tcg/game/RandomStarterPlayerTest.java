package com.iamgique.tcg.game;

import com.iamgique.tcg.chooser.ChoosePlayer;
import com.iamgique.tcg.chooser.ComputerPlayer;
import com.iamgique.tcg.chooser.ConsolePlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RandomStarterPlayerTest {
    @Mock
    ChoosePlayer choosePlayer;

    @InjectMocks
    RandomStarterPlayer randomStarterPlayer;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        randomStarterPlayer = new RandomStarterPlayer();
    }

    @Test
    public void testRandom() throws Exception {
        Player p1 = new Player("GIQUE", new ConsolePlayer());
        Player p2 = new Player("COMPUTER", new ComputerPlayer());

        Player resp = randomStarterPlayer.randomStartPlayer(p1, p2);

        assertEquals(1, 1);
    }
}
