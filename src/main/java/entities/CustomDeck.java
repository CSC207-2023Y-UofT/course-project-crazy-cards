package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import enums.Rank;
import enums.Suit;

public class CustomDeck implements Deck {
    private static final Suit[] SUITS = {Suit.SPADE, Suit.HEART, Suit.CLUB, Suit.DIAMOND};
    private static final Rank[] RANKS = {Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING};

    private static Random random = new Random();

    private int capacity;
    private ArrayList<Card> cards;

    public CustomDeck(int capacity) {
        this.capacity = capacity;
        this.cards = new ArrayList<Card>(capacity);
    }

    public void generate(DeckGenerationParameters params, EffectGenerationParameters effectParams) {
        double totalWeight = params.getTotalWeight();
        for (int i = 0; i < SUITS.length; i++) {
            for (int j = 0; j < RANKS.length; j++) {
                double weight = params.getWeight(SUITS[i], RANKS[j]);
                int count = (int) Math.round(weight / totalWeight * capacity);
                generateCards(SUITS[i], RANKS[j], count, effectParams);
            }
        }
        if (params.getShuffle()) {
            Collections.shuffle(cards);
        }
    }

    @Override
    public void addCardToDeck(Card card) {
        cards.add(card);
    }

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

    @Override
    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public Card playerPickUp() {
        if (cards.size() > 0) {
            return cards.remove(0);
        }
        else {
            return null;
        }
    }
    
    private void generateCards(Suit suit, Rank rank, int count, EffectGenerationParameters effectParams) {
        for (int i = 0; i < count; i++) {
            if (cards.size() >= capacity) {
                break;
            }
            SpecialEffect effect = generateEffect(effectParams);
            cards.add(new Card(suit, rank, effect));
        }
    }

    private SpecialEffect generateEffect(EffectGenerationParameters effectParams) {
        if (effectParams.getChance() > random.nextDouble()) {
            return effectParams.getRandomEffect();
        }
        else {
            return null;
        }
    }
}
