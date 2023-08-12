package ui.windows.game;

import java.util.ArrayList;

public class GameDisplayData {
    private String currentPlayer;
    private ArrayList<CardDisplayData> cards;
    private CardDisplayData currentCard;
    private boolean hasWinner;

    public GameDisplayData(String currentPlayer, ArrayList<CardDisplayData> cards, CardDisplayData currentCard, boolean hasWinner) {
        this.currentPlayer = currentPlayer;
        this.cards = cards;
        this.currentCard = currentCard;
        this.hasWinner = hasWinner;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getHasWinner() {
        return this.hasWinner;
    }

    public CardDisplayData getCurrentCard() {
        return this.currentCard;
    }

    public ArrayList<CardDisplayData> getCards() {
        return cards;
    }
}