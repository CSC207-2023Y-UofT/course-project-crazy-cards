package entities;

import java.util.*;

// A StandardDeck containing 52 Cards, no Jokers
public class StandardDeck implements Deck {

    private ArrayList<Card> cards = new ArrayList<>();

    private static String[] suits = {"Spades" , "Hearts", "Diamond", "Clubs"};
    private static String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public StandardDeck() {
        for (String suit: suits) {
            for (String value: values) {
                this.cards.add(new Card(suit, value));
            }
        }


    }
    public void addCardToDeck(Card card) {
        this.cards.add(card);
    }

    public void removeCardFromDeck(Card card) {
        this.cards.remove(card);
    }

}
