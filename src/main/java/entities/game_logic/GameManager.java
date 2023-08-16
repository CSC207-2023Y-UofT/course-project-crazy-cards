package entities.game_logic;

import entities.card_logic.Card;
import entities.deck_logic.Deck;
import entities.player_logic.ComputerPlayer;
import entities.player_logic.Player;
import enums.Rank;
import enums.Suit;
import enums.TurnAction;

import java.util.ArrayList;

/**
 * Proxy class for Game.
 * Allows interactors to reference a shared game without one being made.
 */
public class GameManager implements CreationAccess,
        GameAccess,
        ObservableGame {
    private Game game;
    private final ArrayList<GameObserver> observers;

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
     * Gets the player who would be next in the game.
     * @return the next player
     */
    @Override
    public Player getNextTurn() {
        if (isGameReady()) {
            return game.getNextTurn();
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
    public void moveNextTurn() {
        if (isGameReady()) {
            game.moveNextTurn();
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
     * Forces a given player to draw cards.
     * @param numCards the number of cards to draw.
     * @param player the player drawing the cards.
     */
    @Override
    public void drawCards(int numCards, Player player) {
        if (isGameReady()) {
            player.drawCards(game, numCards);
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
     * Sets the current suit of this game.
     * @param suit the suit to set
     */
    @Override
    public void setCurrentSuit(Suit suit) {
        if (isGameReady()) {
            game.setCurrentSuit(suit);
        }
    }

    /**
     * Sets the current rank of this game.
     * @param rank the rank to set
     */
    @Override
    public void setCurrentRank(Rank rank) {
        if (isGameReady()) {
            game.setCurrentRank(rank);
        }
    }

    /**
     * Moves the game by the given number of turns, positive for current direction, negative for reverse.
     * @param turns the number of turns to move
     */
    @Override
    public void moveTurns(int turns) {
        if (isGameReady()) {
            game.moveTurns(turns);
        }
    }

    /**
     * Reverses the direction of this game.
     */
    @Override
    public void reverseDirection() {
        if (isGameReady()) {
            game.reverseDirection();
        }
    }

    /**
     * Gets the last attempted move made in this game.
     * @return the last request
     */
    @Override
    public TurnAction getLastRequest() {
        if (isGameReady()) {
            return game.getLastRequest();
        }
        else {
            return null;
        }
    }

    /**
     * Sets the last attempted move made in this game.
     * @param lastRequest the last request
     */
    @Override
    public void setLastRequest(TurnAction lastRequest) {
        if (isGameReady()) {
            game.setLastRequest(lastRequest);
        }
    }

    /**
     * Gets whether the last attempted move made in this game was successful.
     * @return whether the last request was successful
     */
    @Override
    public boolean getSuccess() {
        if (isGameReady()) {
            return game.getLastRequestSuccess();
        }
        else {
            return false;
        }
    }

    /**
     * Sets whether the last attempted move made in this game was successful.
     * @param success whether the last request was successful
     */
    @Override
    public void setSuccess(boolean success) {
        if (isGameReady()) {
            game.setLastRequestSuccess(success);
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

    /**
     * Returns whether the game exists or not.
     * @return If the game exists, returns true, otherwise return false.
     */
    private boolean isGameReady() {
        return game != null;
    }
}
