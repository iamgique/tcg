package com.iamgique.tcg.model;

import com.iamgique.tcg.constants.Action;

import java.util.Optional;

public class Select {
    private final Optional<Card> card;
    private final Action action;

    public Select(Optional<Card> card, Action action) {
        this.card = card;
        this.action = action;
    }

    public Optional<Card> getCard() {
        return card;
    }

    public Action getAction() { return action; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Select select = (Select) o;

        if (action != select.action) return false;
        if (card != null ? !card.equals(select.card) : select.card != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return " Move card: [" + card + "] action: [" + action + "]";
    }
}
