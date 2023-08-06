package entities;

import java.util.ArrayList;

public interface ObservableGame {

    /**
     * Notify all GameObservers observing this Game that there has been a change.
     */
    void notifyGameObservers();

    /**
     * Add a GameObserver to the list of observers.
     */
    void addObserver(GameObserver observer);

    /**
     * Delete the given observer from the list of observers.
     * @param observer The GameObserver to be deleted from the list of observers.
     */
    void deleteObserver(GameObserver observer);

    /**
     * Completely clear the list of observers, such that it is now empty.
     */
    void deleteObservers();

    /**
     * Get the Player whose turn it is currently.
     * @return The Player in this Game who needs to put down a card (i.e. the player whose current turn it is).
     */
    Player getCurrentTurn();

    /**
     * Get the Card that had been played last.
     * @return A Card object which the current Player places their card on top of (i.e. the topmost card in the pile).
     */
    Card getCurrentCard();

    /**
     * Get all the Players in this Game.
     * @return An ArrayList of Players in this game.
     */
    ArrayList<Player> getPlayers();

    /**
     * Determine whether this Game has been won yet.
     * @return True if this Game has been won, false otherwise.
     */
    boolean hasWinner();


}
