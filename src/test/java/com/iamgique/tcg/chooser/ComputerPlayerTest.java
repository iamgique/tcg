package com.iamgique.tcg.chooser;

import com.iamgique.tcg.model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerPlayerTest {

    /*private Optional<Card> getHighestCard(int mana, List<Card> cardInHand) {
        return cardInHand.stream().filter(c -> c.getValue() <= mana).max(Comparator.comparing(Card::getValue));
    }*/

    @InjectMocks
    ComputerPlayer computerPlayer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        computerPlayer = new ComputerPlayer();
    }

    private static List<Card> prepareCardInHand(Integer... values) {
        return stream(values).map(Card::new).collect(toCollection(ArrayList::new));
    }

    @DisplayName("Test get highest card with equals or less than mana should return highest score")
    @Test
    void testGetHighestCard() throws Exception {
        int mana = 8;
        //while (true) {
            List<Card> cardInHand = prepareCardInHand(1, 2, 5, 3, 9);
            Optional<Card> resp = computerPlayer.getHighestCard(mana, cardInHand);
            assertEquals(5, resp.get().getValue());
        //}

    }
}
