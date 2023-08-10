package ui.windows;

import java.util.ArrayList;

public class GameDisplayData {
    private String currentPlayer;
    private ArrayList<CardDisplayData> cards;

    public GameDisplayData(String currentPlayer, ArrayList<CardDisplayData> cards) {
        this.currentPlayer = currentPlayer;
        this.cards = cards;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<CardDisplayData> getCards() {
        return cards;
    }
}