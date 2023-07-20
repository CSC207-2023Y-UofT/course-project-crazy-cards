package entities;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    public Hand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    // removeCard() is only called when a player puts a card down from their hand, thus
    // it should never throw an exception, hence why we don't check if card is in this.cards
    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
