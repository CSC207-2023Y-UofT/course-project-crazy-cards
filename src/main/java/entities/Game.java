package entities;

import java.util.ArrayList;

public class Game implements ObservableGame {
    private Card currentCard;
    private ArrayList<Player> players;

    private Deck gameDeck;

    private Player winner;

    private Player currentTurn;

    private ArrayList<GameObserver> observers;

    /**
     * Construct a new card game given a particular deck and set of players.
     * @param deck The deck of cards that belongs to this Game.
     * @param players The Players who will be playing this card Game.
     */
    public Game(Deck deck, ArrayList<Player> players) {
        this.gameDeck = deck;
        this.players = new ArrayList<>(players);
        this.currentTurn = this.players.get(0);
    }

    /**
     * Get the Player whose turn it is currently.
     * @return The Player in this Game whose turn it is who needs to put down a card.
     */
    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * Change the turn of the Player from the current Player to the next in the Game.
     * This method assumes that the Player before is not the winner of the Game, otherwise it would not be called.
     */
    private void changeCurrentTurn() {
        int currentPlayerIndex = this.players.indexOf(this.currentTurn);
        if(currentPlayerIndex == (this.players.size() -  1)) {
            this.currentTurn = this.players.get(0);
        }
        else {
            this.currentTurn = this.players.get(currentPlayerIndex + 1);
        }
    }

    /**
     * Get the Card that had been played last.
     * @return A Card object which the current Player must place their card on top of.
     */
    public Card getCurrentCard() {
        return this.currentCard;
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
        if (card.getValue().equals("8")) {
            return true;
        } else {return (card.getValue().equals(currentCard.getValue()) | card.getSuit().equals(currentCard.getSuit()));}
    }

    /**
     * Get the Deck used for this Game.
     * @return a Deck object belonging to this Game.
     */
    public Deck getGameDeck() {
        return this.gameDeck;
    }

    /**
     * Notify all GameObservers observing this Game that there has been a change.
     */
    @Override
    public void notifyGameObservers() {
        for(GameObserver observer : observers) {
            observer.updateGameObserver(this);
        }
    }

    /**
     * Add a GameObserver to the list of observers.
     *
     * @param observer The GameObserver to be added to the list of observers.
     */
    @Override
    public void addObserver(GameObserver observer) {
        this.observers.add(observer);

    }

    /**
     * Delete the given observer from the list of observers.
     *
     * @param observer The GameObserver to be deleted from the list of observers.
     */
    @Override
    public void deleteObserver(GameObserver observer) {
        if (this.observers.contains(observer)) {
            this.observers.remove(observer);
        }

    }

    /**
     * Delete clear the list of observers, such that it is now empty.
     */
    @Override
    public void deleteObservers() {
        this.observers.clear();
    }
}
