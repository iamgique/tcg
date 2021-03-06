package com.iamgique.tcg.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

public class Card {
    private int value;

    public Card(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<Card> list(Integer... values) {
        return stream(values).map(Card::new).collect(toCollection(ArrayList::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;
        if (value != card.value) return false;
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
