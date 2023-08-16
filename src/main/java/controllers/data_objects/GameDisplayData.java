package controllers.data_objects;

import java.util.ArrayList;
import java.util.HashMap;

import enums.TurnAction;

public class GameDisplayData {
    private String currentPlayer;
    private ArrayList<CardDisplayData> cards;
    private CardDisplayData currentCard;
    private boolean hasWinner;
    private HashMap<String, Integer> playersAndNumCards;
    private TurnAction lastMove;
    private boolean lastMoveSuccess;
    private boolean resetFeedbackMessage;

    /**
     * Constructor for the data to be used in displaying the game.
     * Requires data to be set.
     */
    public GameDisplayData() {

    }

    /**
     * Sets the current player.
     * @param currentPlayer The current player's name
     */
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Sets the cards in the current player's hand.
     * @param cards The cards in the current player's hand.
     */
    public void setCards(ArrayList<CardDisplayData> cards) {
        this.cards = cards;
    }

    /**
     * Sets the current card in play.
     * @param currentCard The current card in play.
     */
    public void setCurrentCard(CardDisplayData currentCard) {
        this.currentCard = currentCard;
    }

    /**
     * Sets the other players (minus current player) and their respective cards.
     * @param playersAndNumCards The other players in a HashMap, with the keys as the players' names as Strings, and the respective
     * number of cards they have, as an Integer.
     */
    public void setPlayersAndNumCards(HashMap<String, Integer> playersAndNumCards) {
        this.playersAndNumCards = playersAndNumCards;
    }

    /**
     * Sets whether the game has a winner.
     * @param hasWinner whether the game has a winner.
     */
    public void setHasWinner(boolean hasWinner) {
        this.hasWinner = hasWinner;
    }

    /**
     * Sets the last action taken by the current player.
     * @param move The last action taken by the current player.
     * @param success Whether the last action was successful.
     */
    public void setLastAction(TurnAction move, boolean success) {
        this.lastMove = move;
        this.lastMoveSuccess = success;
        this.resetFeedbackMessage = false;
    }

    /**
     * Resets the feedback message to the default message.
     */
    public void resetFeedback() {
        this.resetFeedbackMessage = true;
    }

    /**
     * Getter method for the feedback message to be displayed to the current player.
     * @return The feedback message to be displayed to the current player, as a String.
     */
    public String getFeedbackMessage() {
        if (resetFeedbackMessage || lastMove == TurnAction.START) {
            return "Select a card to play, or draw a card.";
        }

        String message = "";
        if (lastMove == TurnAction.DRAW) {
            if (lastMoveSuccess) {
                message = "You drew a card.";
            } else {
                message = "You cannot draw a card right now.";
            }
        } else if (lastMove == TurnAction.PLAY) {
            if (lastMoveSuccess) {
                message = "You played a card.";
            } else {
                message = "You cannot play that card.";
            }
        } else if (lastMove == TurnAction.SKIP) {
            if (lastMoveSuccess) {
                message = "You passed your turn.";
            } else {
                message = "You cannot skip your turn right now.";
            }
        } 
        return message;
    }

    /**
     * Getter method for the current player, whose turn it is.
     * @return The current player's name, as a String.
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets whether the game has a winner.
     * @return whether the game has a winner.
     */
    public boolean getHasWinner() {
        return this.hasWinner;
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
