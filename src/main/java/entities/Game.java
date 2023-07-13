package entities;

import java.io.*;
import java.util.*;

public class Game {

    private Deck deck;

    public Game() {
        this.deck = new Deck();
    }

    public static void main(String[] args) throws IOException {
    }

    public Card getDeckCard() {
        return deck.removeCard();
    }

    public void playCard(Card card) {
    }

}
