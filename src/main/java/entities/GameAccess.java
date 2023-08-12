package entities;

import java.util.ArrayList;

public interface GameAccess {

    /**
     * Get the Player whose turn it is currently.
     * @return The Player in this Game whose turn it is, and needs to put down a card.
     */
    Player getCurrentTurn();

    void playCard(Player player, Card card);

    void pickUpCard(Player player);

    Card selectRandomValidCard(ComputerPlayer player);

    // TODO: docs
    Card getCurrentCard();

    Card getDrawnCard();

    /**
     * Change the turn of the Player from the current Player to the next in the Game.
     * The Player whose turn it is now should not have picked up.
     * This method assumes that the previous Player is not the winner of the Game, otherwise it would not be called.
     */
    void changeCurrentTurn();

    /**
     * Set the winner of this game to the given Player.
     * @param winner The Player who has won the Game.
     */
    void setWinner(Player winner);

    /**
     * Get all the Players in this Game.
     * @return an ArrayList of Players in this game.
     */
    ArrayList<Player> getPlayers();

    /**
     * Determine whether this Game has been won yet.
     * @return True if this Game has been won, false otherwise.
     */
    boolean hasWinner();

    /**
     * Determine whether this Card is a valid Card to be placed onto this Game given the currentCard of this Game.
     * @param card The Card to be placed down.
     * @return True if this is a valid Card, false otherwise.
     */
    boolean isValidCard(Card card);

    /**
     * Notify all GameObservers observing this Game that there has been a change.
     */
    void notifyGameObservers();

    /**
     * Return if the current Player has picked up a card.
     * @return True iff the current Player has picked up a Card from this Game's Deck.
     */
    boolean getCurrentTurnHasPickedUp();
}