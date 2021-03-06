package com.iamgique.tcg.chooser;

import com.iamgique.tcg.constants.Action;
import com.iamgique.tcg.model.Card;
import com.iamgique.tcg.model.Select;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {
    @InjectMocks
    ComputerPlayer computerPlayer;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        computerPlayer = new ComputerPlayer();
    }

    @Test
    public void testGetHighestCardWithEqualsOrLessThanManaShouldReturnHighestScore() {
        int mana = 8;
        List<Card> cardInHand = Card.list(1, 2, 5, 3, 9);
        Optional<Card> resp = computerPlayer.getHighestCard(mana, cardInHand);
        assertEquals(5, resp.get().getValue());
    }

    @Test
    public void testComputerSelectHit() {
        int health = 30;
        int mana = 5;
        int opponentHealth = 8;
        List<Card> cardInHand = Card.list(1, 2, 5, 3);

        List<Select> actual = new ArrayList<>();

        while (mana > -1) {
            Select resp = computerPlayer.playerSelect(health, mana, cardInHand, opponentHealth);
            actual.add(resp);
            mana-=1;
        }

        for(int i = 0; i <= 5; i++) {
            assertEquals(Action.HIT, actual.get(i).getAction());
        }

        assertEquals(5, actual.get(0).getCard().get().getValue());
        assertEquals(3, actual.get(1).getCard().get().getValue());
        assertEquals(3, actual.get(2).getCard().get().getValue());
        assertEquals(2, actual.get(3).getCard().get().getValue());
        assertEquals(1, actual.get(4).getCard().get().getValue());

    }

    @Test
    public void testComputerSelectHitAndKillShouldReturnActionEqualsHitAndCardNinePoint() {
        int health = 30;
        int mana = 10;
        int opponentHealth = 8;
        List<Card> cardInHand = Card.list(1, 2, 5, 3, 9);

        Select resp = computerPlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(Action.HIT, resp.getAction());
        assertEquals(9, resp.getCard().get().getValue());
    }

    @Test
    public void testComputerSelectHealShouldReturnActionEqualsHealAndCardNinePoint() {
        int health = 8;
        int mana = 10;
        int opponentHealth = 15;
        List<Card> cardInHand = Card.list(1, 2, 5, 3, 9);

        Select resp = computerPlayer.playerSelect(health, mana, cardInHand, opponentHealth);
        assertEquals(Action.HEAL, resp.getAction());
        assertEquals(9, resp.getCard().get().getValue());
    }

}
