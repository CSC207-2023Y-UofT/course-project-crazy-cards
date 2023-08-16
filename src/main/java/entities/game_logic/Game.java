package entities.game_logic;

import entities.card_logic.Card;
import entities.card_logic.SpecialEffect;
import entities.deck_logic.Deck;
import entities.player_logic.Player;
import enums.Rank;
import enums.Suit;
import enums.TurnAction;

import java.util.ArrayList;

public class Game {
    private Card currentCard;
    private Card currentDrawnCard;
    private final ArrayList<Player> players;
    private final Deck gameDeck;
    private Player winner;
    private Player currentTurn;
    private boolean currentTurnHasPickedUp = false;
    private int currentPlayerIndex;
    private boolean reverseDirection;
    private TurnAction lastRequest;
    private boolean lastRequestSuccess;

    /**
     * Construct a new Game given a particular deck and set of players.
     * @param deck The deck of cards that belongs to this Game.
     * @param players The Players who will be playing this Game.
     */
    public Game(Deck deck, ArrayList<Player> players) {
        this.gameDeck = deck;
        this.players = new ArrayList<>(players);
        this.currentPlayerIndex = 0;
        this.currentTurn = this.players.get(0);
        this.reverseDirection = false;
    }

    /**
     * Get the Player whose turn it is currently.
     * @return The Player in this Game whose turn it is, and needs to put down a card.
     */
    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * Get the player that would be next in the game.
     * @return The Player in this Game who would be next to play.
     */
    public Player getNextTurn() {
        int shift = reverseDirection ? -1 : 1;
        int nextPlayer = (currentPlayerIndex + shift) % this.players.size();
        return this.players.get(nextPlayer);
    }

    /**
     * Change the turn of the Player from the current Player to the next in the Game.
     * The Player whose turn it is now should not have picked up.
     * This method assumes that the previous Player is not the winner of the Game, otherwise it would not be called.
     */
    public void moveNextTurn() {
        int shift = reverseDirection ? -1 : 1;
        int nextPlayer = (currentPlayerIndex + shift) % this.players.size();
        this.currentPlayerIndex = nextPlayer;
        this.currentTurn = this.players.get(nextPlayer);
        currentTurnHasPickedUp = false;
    }

    /**
     * Moves the game forward by the given number of turns.
     * @param turns the number of turns to move.
     */
    public void moveTurns(int turns) {
        int shift = reverseDirection ? -turns : turns;
        int nextPlayer = (currentPlayerIndex + shift) % this.players.size();
        this.currentPlayerIndex = nextPlayer;
        this.currentTurn = this.players.get(nextPlayer);
        currentTurnHasPickedUp = false;
    }

    /**
     * Reverse the direction of turns.
     */
    public void reverseDirection() {
        this.reverseDirection = !this.reverseDirection;
    }

    /**
     * Sets the rank of the current card.
     * @param rank The Rank to set the current Rank to.
     */
    public void setCurrentRank(Rank rank) {
        this.currentCard.setRank(rank);
    }

    /**
     * Set the suit of the current card.
     * @param suit The Suit to set the current Suit to.
     */
    public void setCurrentSuit(Suit suit) {
        this.currentCard.setSuit(suit);
    }

    /**
     * Get the Card that had been played last.
     * @return A Card object which the current Player must place their card on top of.
     */
    public Card getCurrentCard() {
        return this.currentCard;
    }

    /**
     * Get the Card that was drawn last.
     * @return A Card object which the current Player has drawn from the deck.
     */
    public Card getDrawnCard() {
        return this.currentDrawnCard;
    }

    /**
     * A Player will play the given card object to be put down into the Game as the new currentCard.
     * @param card A Card object played by a Player in this Game.
     */
    public void putCardDown(Card card) {
        if (currentCard != null) {
            this.gameDeck.addCardToDeck(this.currentCard);
        }
        this.currentCard = card;
    }

    /**
     * Set the winner of this game to the given Player.
     * @param winner The Player who has won the Game.
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Get all the Players in this Game.
     * @return an ArrayList of Players in this game.
     */
    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(this.players);
    }

    /**
     * Determine whether this Game has been won yet.
     * @return True if this Game has been won, false otherwise.
     */
    public boolean hasWinner() {
        return this.winner != null;
    }

    /**
     * Get the Winner of this Game.
     * This method is only to be called when the Game has a winner.
     * @return The Player who has won this Game.
     */
    public Player getWinner() {
        return this.winner;
    }

    /**
     * Determine whether this Card is a valid Card to be placed onto this Game given the currentCard of this Game.
     * @param card The Card to be placed down.
     * @return True if this is a valid Card, false otherwise.
     */
    public boolean isValidCard(Card card) {
        SpecialEffect specialEffect = card.getSpecialEffect();
        if (specialEffect != null && specialEffect.getAlwaysPlayable()) {
            return true;
        } 
        else {
            return (card.getRank().equals(currentCard.getRank()) | card.getSuit().equals(currentCard.getSuit()));
        }
    }

    /**
     * Get the Deck used for this Game.
     * @return a Deck object belonging to this Game.
     */
    public Deck getGameDeck() {
        return this.gameDeck;
    }

    /**
     * Get the top Card of this Game's Deck.
     * @return A Card object representing the top Card of this Game's Deck.
     */
    public Card getTopCard() {
        this.currentDrawnCard = this.gameDeck.removeCardFromDeck();
        return this.currentDrawnCard;
    }

    /**
     * Return if the current Player has picked up a card.
     * @return True iff the current Player has picked up a Card from this Game's Deck.
     */
    public boolean getCurrentTurnHasPickedUp() { return this.currentTurnHasPickedUp;}

    /**
     * Set currentTurnHasPickUp to true.
     * This method is only called when a Player picks up a Card from this Game's deck.
     */
    public void setCurrentTurnHasPickedUpTrue() {
        currentTurnHasPickedUp = true;
    }

    /**
     * Gets the last attempted move made.
     * @return The last attempted move made.
     */
    public TurnAction getLastRequest() {
        return lastRequest;
    }

    /**
     * Sets the last attempted move made.
     * @param lastRequest The last attempted move made.
     */
    public void setLastRequest(TurnAction lastRequest) {
        this.lastRequest = lastRequest;
    }

    /**
     * Gets whether the last attempted move was successful.
     * @return Whether the last attempted move was successful.
     */
    public boolean getLastRequestSuccess() {
        return lastRequestSuccess;
    }

    /**
     * Sets whether the last attempted move was successful.
     * @param lastRequestSuccess Whether the last attempted move was successful.
     */
    public void setLastRequestSuccess(boolean lastRequestSuccess) {
        this.lastRequestSuccess = lastRequestSuccess;
    }
}
