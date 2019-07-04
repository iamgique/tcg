package com.iamgique.tcg.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class RandomStarterPlayerTest {
    @InjectMocks
    RandomStarterPlayer randomStarterPlayer;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        randomStarterPlayer = new RandomStarterPlayer();
    }

    @Test
    public void testRandom() throws Exception {
        assertEquals(1, 1);
    }
}
