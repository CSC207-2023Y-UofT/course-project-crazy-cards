package entities;

import java.util.ArrayList;

/**
 * Proxy class for Game.
 * 
 * Allows interactors to reference a shared game without one being made.
 */
public class GameManager implements CreationAccess,
                                    GameAccess,
                                    ObservableGame {
    private Game game;
    private ArrayList<GameObserver> observers;

    /**
     * Constructs a GameManager with empty attributes.
     */
    public GameManager() {
        this.game = null;
        this.observers = new ArrayList<>();
    }

    /**
     * Updates observers of this game.
     */
    @Override
    public void notifyGameObservers() {
        for (GameObserver observer : observers) {
            observer.updateGameObserver(this);
        }
    }

    /**
     * Adds an observer to this game.
     */
    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from this game.
     */
    @Override
    public void deleteObserver(GameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Removes all observers from this game.
     */
    @Override
    public void deleteObservers() {
        observers.clear();
    }

    /**
     * Gets the player whose turn it currently is.
     * @return the current player
     */
    @Override
    public Player getCurrentTurn() {
        if (isGameReady()) {
            return game.getCurrentTurn();
        }
        else {
            return null;
        }
    }

    /**
     * Gets the current (last played) card in the game.
     * @return the current card
     */
    @Override
    public Card getCurrentCard() {
        if (isGameReady()) {
            return game.getCurrentCard();
        }
        else {
            return null;
        }
    }

    /**
     * Gets the most recently drawn card this game.
     * @return the most recently drawn card
     */
    @Override
    public Card getDrawnCard() {
        if (isGameReady()) {
            return game.getDrawnCard();
        }
        else {
            return null;
        }
    }

    /**
     * Changes the current turn to the next player.
     */
    @Override
    public void changeCurrentTurn() {
        if (isGameReady()) {
            game.changeCurrentTurn();
        }
    }

    /**
     * Sets the winner of this game.
     * @param winner the player who won the game
     */
    @Override
    public void setWinner(Player winner) {
        if (isGameReady()) {
            game.setWinner(winner);
        }
    }

    /**
     * Gets the players of this game.
     * @return the players of this game
     */
    @Override
    public ArrayList<Player> getPlayers() {
        if (isGameReady()) {
            return game.getPlayers();
        }
        else {
            return null;
        }
    }

    /**
     * Gets if the game has been won yet.
     * @return whether the game has a winner
     */
    @Override
    public boolean hasWinner() {
        if (isGameReady()) {
            return game.hasWinner();
        }
        else {
            return false;
        }
    }

    /**
     * Gets whether the given card is valid to play.
     * @param card the card to check
     * @return whether the card is valid
     */
    @Override
    public boolean isValidCard(Card card) {
        if (isGameReady()) {
            return game.isValidCard(card);
        }
        else {
            return false;
        }
    }

    /**
     * Gets whether the current player has picked up a card this turn.
     * @return whether the current player has picked up a card
     */
    @Override
    public boolean getCurrentTurnHasPickedUp() {
        if (isGameReady()) {
            return game.getCurrentTurnHasPickedUp();
        }
        else {
            return false;
        }
    }

    /**
     * Plays the given card for the given player.
     * @param player the player playing the card
     * @param card the card to play
     */
    @Override
    public void playCard(Player player, Card card) {
        if (isGameReady()) {
            player.playCard(game, card);
        }
    }

    /**
     * Draws a card for the given player.
     * @param player the player drawing the card
     */
    @Override
    public void pickUpCard(Player player) {
        if (isGameReady()) {
            player.pickUpCard(game);
        }
    }

    /**
     * Selects a random valid card for the given computer player from its hand.
     * @param player the computer player
     * @return the random valid card, null if none.
     */
    @Override
    public Card selectRandomValidCard(ComputerPlayer player) {
        if (isGameReady()) {
            return player.selectRandomValidCard(game);
        }
        else {
            return null;
        }
    }

    /**
     * Sets this game's attributes.
     * @param players the players of this game
     * @param deck the deck of this game
     */
    @Override
    public void buildGame(ArrayList<Player> players, Deck deck) {
        game = new Game(deck, players);
        Card firstCard = deck.removeCardFromDeck();
        game.putCardDown(firstCard);
    }

    private boolean isGameReady() {
        return game != null;
    }
}
