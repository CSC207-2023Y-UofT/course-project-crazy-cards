package ui.windows.game;

import java.util.ArrayList;

public class GameDisplayData {
    private String currentPlayer;
    private ArrayList<CardDisplayData> cards;
    private CardDisplayData currentCard;

    public GameDisplayData(String currentPlayer, ArrayList<CardDisplayData> cards, CardDisplayData currentCard) {
        this.currentPlayer = currentPlayer;
        this.cards = cards;
        this.currentCard = currentCard;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public CardDisplayData getCurrentCard() {
        return this.currentCard;
    }

    public ArrayList<CardDisplayData> getCards() {
        return cards;
    }
}