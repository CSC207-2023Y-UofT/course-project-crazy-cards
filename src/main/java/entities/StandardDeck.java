package entities;

import enums.Rank;
import enums.Suit;

import java.util.ArrayList;
import java.util.Collections;

// A StandardDeck containing 52 Cards, no Jokers
public class StandardDeck implements Deck {

    private final ArrayList<Card> cards = new ArrayList<>();

    private final static Suit[] suits = {Suit.SPADE, Suit.HEART, Suit.DIAMOND, Suit.CLUB};
    private final static Rank[] rank = {Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN,
            Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING};

    /**
     * Construct an ArrayList that contains a standard 52 card deck that will be used for the game.
     */
    public StandardDeck() {
        for (Suit suit : suits) {
            for (Rank rank : rank) {
                this.cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    /**
     * Add the given card to the end of the ArrayList containing the deck of cards in play.
     * @param card The instance of the Card class that is being added to the current deck.
     */
    public void addCardToDeck(Card card) {
        this.cards.add(card);}

    /**
     * Remove a Card from the top of the Deck, as a Player would when picking up a Card in-game.
     * @return a Card from the top of the Deck.
     */
    public Card playerPickUp() {
        if(cards.size() > 0) {
            return this.cards.remove(0);
        }
        return null;
    }

    /**
     * Remove a Card from this randomly shuffled Deck, from the *end* of the ArrayList.
     * @return A Card instance if this Deck is nonempty, otherwise return null.
     */
    public Card removeCardFromDeck () {
            if (cards.size() >= 1) {
                Card card = this.cards.get(cards.size() - 1);
                this.cards.remove(cards.size() - 1);
                return card;
            } else {
                return null;
            }
        }

    /**
     * Get for all the cards in this Deck.
     * @return A new ArrayList of the Cards in this Deck.
     */
    public ArrayList<Card> getCards() {
            return new ArrayList<>(cards);
        }
}