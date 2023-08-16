package entities.deck_logic;

import enums.Rank;
import enums.Suit;

import java.util.HashMap;

/**
 * Parameters for generating a deck. Represents a 2D matrix of weights for each combination of suit and rank.
 */
public class DeckGenerationParameters {
    private static final HashMap<Suit, Integer> SUIT_TO_INDEX = new HashMap<>() {
        {
            put(Suit.CLUB, 0);
            put(Suit.SPADE, 1);
            put(Suit.HEART, 2);
            put(Suit.DIAMOND, 3);
        }
    };

    private final static HashMap<Rank, Integer> RANK_TO_INDEX = new HashMap<>() {
        {
            put(Rank.ACE, 0);
            put(Rank.TWO, 1);
            put(Rank.THREE, 2);
            put(Rank.FOUR, 3);
            put(Rank.FIVE, 4);
            put(Rank.SIX, 5);
            put(Rank.SEVEN, 6);
            put(Rank.EIGHT, 7);
            put(Rank.NINE, 8);
            put(Rank.TEN, 9);
            put(Rank.JACK, 10);
            put(Rank.QUEEN, 11);
            put(Rank.KING, 12);
        }
    };

    private final double[][] weights;
    private boolean shuffle;

    /**
     * Creates a new DeckGenerationParameters object.
     * @param shuffle Whether this deck should be shuffled.
     */
    public DeckGenerationParameters(boolean shuffle) {
        this.weights = new double[4][13];
        this.shuffle = shuffle;
    }

    /**
     * Sets the weight of a given suit and rank.
     * Weight dictates the amount of cards of a given suit and rank that will be generated.
     * The amount of cards generated is equal to 
     * the card type's weight divided by the total weight of the deck, multiplied by the size of the deck.
     * @param suit The suit of the card.
     * @param rank The rank of the card.
     * @param weight The weight of the card.
     */
    public void setWeight(Suit suit, Rank rank, double weight) {
        this.weights[SUIT_TO_INDEX.get(suit)][RANK_TO_INDEX.get(rank)] = weight;
    }

    /**
     * Shortcut for setting the weights of a row, i.e. a suit.
     * See {@link #setWeight} for more information.
     * @param suit The suit of the cards.
     * @param weight The weights of the cards.
     */
    public void setRowWeight(Suit suit, double[] weight) {
        int suitIndex = SUIT_TO_INDEX.get(suit);
        System.arraycopy(weight, 0, this.weights[suitIndex], 0, weight.length);
    }

    /**
     * Shortcut for setting the weights of a column, i.e. a rank.
     * See {@link #setWeight} for more information.
     * @param rank The rank of the cards.
     * @param weight The weights of the cards.
     */
    public void setColumnWeight(Rank rank, double[] weight) {
        int rankIndex = RANK_TO_INDEX.get(rank);
        for (int i = 0; i < weight.length; i++) {
            this.weights[i][rankIndex] = weight[i];
        }
    }

    /**
     * Shortcut for getting the weight of a card type.
     * See {@link #setWeight} for more information.
     * @param suit The suit of the card.
     * @param rank The rank of the card.
     */
    public double getWeight(Suit suit, Rank rank) {
        return this.weights[SUIT_TO_INDEX.get(suit)][RANK_TO_INDEX.get(rank)];
    }

    /**
     * Gets the total weight of the deck.
     * See {@link #setWeight} for more information.
     * @return The total weight of the deck.
     */
    public double getTotalWeight() {
        double total = 0;
        for (double[] weight : this.weights) {
            for (int j = 0; j < weight.length; j++) {
                total += weight[j];
            }
        }
        return total;
    }

    /**
     * Gets whether this deck should be shuffled.
     * @return Whether this deck should be shuffled.
     */
    public boolean getShuffle() {
        return this.shuffle;
    }
}
