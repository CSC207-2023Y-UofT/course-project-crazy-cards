package entities.deck_logic;

import entities.card_logic.Card;
import entities.card_logic.EffectGenerationParameters;
import entities.card_logic.SpecialEffect;
import enums.Rank;
import enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a customizable, ordered deck of cards.
 */
public class CustomDeck implements Deck {
    private static final Suit[] SUITS = {Suit.SPADE, Suit.HEART, Suit.CLUB, Suit.DIAMOND};
    private static final Rank[] RANKS = {Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING};

    private static final Random random = new Random();

    private final int capacity;
    private final ArrayList<Card> cards;

    /**
     * Constructs a CustomDeck with a given max amount of cards.
     * @param capacity The capacity of the deck.
     */
    public CustomDeck(int capacity) {
        this.capacity = capacity;
        this.cards = new ArrayList<>(capacity);
    }

    /**
     * Fills this instance's list of cards with the given parameters.
     * @param deckParams The parameters for the deck.
     * @param effectParams The parameters for the effects.
     */
    public void generate(DeckGenerationParameters deckParams, EffectGenerationParameters effectParams) {
        double totalWeight = deckParams.getTotalWeight();
        for (Suit suit : SUITS) {
            for (Rank rank : RANKS) {
                double weight = deckParams.getWeight(suit, rank);
                int count = (int) Math.round(weight / totalWeight * capacity);
                generateCards(suit, rank, count, effectParams);
            }
        }
        if (deckParams.getShuffle()) {
            Collections.shuffle(cards);
        }
    }

    /**
     * Adds the given card to the end of the deck.
     * @param card The card to add.
     */
    @Override
    public void addCardToDeck(Card card) {
        cards.add(card);
    }

    /**
     * Pops a card from the end of the deck.
     * @return The card removed.
     */
    @Override
    public Card removeCardFromDeck() {
        if (cards.size() >= 1) {
            Card card = cards.get(cards.size() - 1);
            cards.remove(cards.size() - 1);
            return card;
        }
        else {
            return null;
        }
    }

    /**
     * Gets the cards in the deck.
     * @return The cards in the deck.
     */
    @Override
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Pops a card from the top of the deck.
     * @return the top card.
     */
    @Override
    public Card playerPickUp() {
        if (cards.size() > 0) {
            return cards.remove(0);
        }
        else {
            return null;
        }
    }
    
    /**
     * Generates cards with the given parameters.
     * @param suit the suit of the card.
     * @param rank the rank of the card.
     * @param count the number of cards to generate.
     * @param effectParams parameters for generating special effects.
     */
    private void generateCards(Suit suit, Rank rank, int count, EffectGenerationParameters effectParams) {
        for (int i = 0; i < count; i++) {
            if (cards.size() >= capacity) {
                break;
            }
            SpecialEffect effect = generateEffect(effectParams);
            cards.add(new Card(suit, rank, effect));
        }
    }

    /**
     * Generates a special effect with the given parameters, respecting chance and weight.
     * See  for more information.
     * @param effectParams parameters for generating special effects.
     */
    private SpecialEffect generateEffect(EffectGenerationParameters effectParams) {
        if (effectParams.getChance() > random.nextDouble()) {
            return effectParams.getRandomEffect();
        }
        else {
            return null;
        }
    }
}
