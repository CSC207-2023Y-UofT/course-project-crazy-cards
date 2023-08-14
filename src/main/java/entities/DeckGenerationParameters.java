package entities;

import java.util.HashMap;

import enums.Rank;
import enums.Suit;

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

    private double[][] weights;
    private boolean shuffle;

    public DeckGenerationParameters(boolean shuffle) {
        this.weights = new double[4][13];
        this.shuffle = shuffle;
    }

    public void setWeight(Suit suit, Rank rank, double weight) {
        this.weights[SUIT_TO_INDEX.get(suit)][RANK_TO_INDEX.get(rank)] = weight;
    }

    public void setRowWeight(Suit suit, Rank start, double[] weight) {
        int suitIndex = SUIT_TO_INDEX.get(suit);
        int startIndex = RANK_TO_INDEX.get(start);
        for (int i = 0; i < weight.length; i++) {
            this.weights[suitIndex][startIndex + i] = weight[i];
        }
    }

    public void setColumnWeight(Rank rank, double[] weight) {
        int rankIndex = RANK_TO_INDEX.get(rank);
        for (int i = 0; i < weight.length; i++) {
            this.weights[i][rankIndex] = weight[i];
        }
    }

    public double getWeight(Suit suit, Rank rank) {
        return this.weights[SUIT_TO_INDEX.get(suit)][RANK_TO_INDEX.get(rank)];
    }

    public double getTotalWeight() {
        double total = 0;
        for (int i = 0; i < this.weights.length; i++) {
            for (int j = 0; j < this.weights[i].length; j++) {
                total += this.weights[i][j];
            }
        }
        return total;
    }

    public boolean getShuffle() {
        return this.shuffle;
    }
}
