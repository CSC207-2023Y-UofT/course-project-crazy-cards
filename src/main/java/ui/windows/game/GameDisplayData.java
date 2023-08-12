package ui.windows.game;

import java.util.ArrayList;
import java.util.HashMap;

public class GameDisplayData {
    private String currentPlayer;
    private ArrayList<CardDisplayData> cards;
    private CardDisplayData currentCard;
    private HashMap<String, Integer> playersAndNumCards;

    /**
     * Constructor for the data to be used in displaying the game.
     * @param currentPlayer The player whose turn it currently is.
     * @param cards The cards of the current player.
     * @param currentCard The current card in play on the table that the current player needs to play on.
     * @param playersAndNumCards The rest of the players (minus current player) and their respective number of cards.
     */
    public GameDisplayData(String currentPlayer, ArrayList<CardDisplayData> cards, CardDisplayData currentCard, HashMap<String, Integer> playersAndNumCards) {
        this.currentPlayer = currentPlayer;
        this.cards = cards;
        this.currentCard = currentCard;
        this.playersAndNumCards = playersAndNumCards;
    }

    /**
     * Getter method for the current player, whose turn it is.
     * @return The current player's name, as a String.
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Getter method for the current card in play, on the table.
     * @return The current card in play, as CardDisplayData.
     */
    public CardDisplayData getCurrentCard() {
        return this.currentCard;
    }

    /**
     * Getter method for the cards in the hand of the current player.
     * @return The cards the current player has, in an ArrayList of CardDisplayData.
     */
    public ArrayList<CardDisplayData> getCards() {
        return cards;
    }

    /**
     * Getter method for the other players (minus current player) and their respective cards.
     * @return The other players in a HashMap, with the keys as the players' names as Strings, and the respective
     * number of cards they have, as an Integer.
     */
    public HashMap<String, Integer> getPlayersAndNumCards() {
        return playersAndNumCards;
    }
}