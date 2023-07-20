package entities;

import java.io.*;
import java.util.*;

public class Deck {

    private ArrayList<Card> cards;

    public void addCardToDeck(Card card) {
        this.cards.add(card);
    }

    private void removeCardFromDeck(Card card) {
        this.cards.remove(card);
    }

}
