package use_cases;

import entities.game_logic.GameObserver;
import entities.card_logic.Card;
import entities.game_logic.GameAccess;
import entities.player_logic.Player;
import enums.TurnAction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is an Observer of a Game. It provides information that will eventually be relayed
 * to the controller for presentation, such as the current Player and their cards, the names and number of Cards
 * of the opponent Players, as well as the last Card played and if there is a winner to the Game.
 */
public class GameState implements GameObserver {
    private Player currentPlayer;
    private Card currentCard;
    private Card currentDrawnCard;
    private ArrayList<Card> currentPlayerCards;
    private HashMap<Player, Integer> playersAndCards = new HashMap<>();
    private boolean hasWinner;
    private TurnAction lastRequest;
    private boolean lastRequestSuccess;

    /**
     * Given a Game, construct a GameState object which keeps track of the Game's current Player,
     * current Card, the current Player's Cards, the other players and their respective number of Cards,
     * as well as if the Game has a winner.
     * @param game The Game containing the information to update this GameState.
     */
    public GameState(GameAccess game) {

    }

    /**
     * Update this GameState given the ObservableGame.
     * @param game An ObservableGame that has notified this GameObserver of an update.
     */
    @Override
    public void updateGameObserver(GameAccess game) {
        // Add the Player whose turn it was previous to the HashMap and remove the new current Player from it.
        this.currentPlayer = game.getCurrentTurn();
        this.playersAndCards = updatePlayersAndCards(game.getPlayers());
        this.playersAndCards.remove(this.currentPlayer);
        this.currentCard = game.getCurrentCard();
        this.currentDrawnCard = game.getDrawnCard();
        this.hasWinner = game.hasWinner();
        this.currentPlayerCards = this.currentPlayer.getCards();
        this.lastRequest = game.getLastRequest();
        this.lastRequestSuccess = game.getSuccess();
    }

    /**
     * Get the current Player of the Game.
     * @return A Player representing the current turn of the Game.
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Get the last card played in the Game.
     * @return The current Card in the Game, (most recently played Card).
     */
    public Card getCurrentCard() {
        return this.currentCard;
    }

    /**
     * Get the last card drawn in the Game.
     * @return The current Card in the Game, (most recently drawn Card).
     */
    public Card getCurrentDrawnCard() {
        return this.currentDrawnCard;
    }

    /**
     * Get the Cards of the current Player.
     * @return A new ArrayList of the Cards belonging to the current Player.
     */
    public ArrayList<Card> getCurrentPlayerCards() {
        return new ArrayList<>(currentPlayerCards);
    }

    /**
     * Get whether the current Game being observed by this GameState has a winner.
     * @return True iff the Game has a winner.
     */
    public boolean getHasWinner() {
        return this.hasWinner;
    }

    /**
     * Get the Players other than the current Player and their respective number of Cards.
     * @return A HashMap with keys being the other Player entities, and values being their respective number of cards.
     */
    public HashMap<Player, Integer> getPlayersAndCards() {
        return playersAndCards;
    }

    /**
     * Get the last request made by the current Player.
     * @return A TurnAction representing the last request made by the current Player.
     */
    public TurnAction getLastRequest() {
        return this.lastRequest;
    }

    /**
     * Get whether the last request made by the current Player was successful.
     * @return True iff the last request made by the current Player was successful.
     */
    public boolean getLastRequestSuccess() {
        return this.lastRequestSuccess;
    }

    /**
     * Update the HashMap of Players and their respective number of Cards.
     * @param players The list of players in the game.
     * @return A HashMap with keys being the opponents, and values being their respective number of cards.
     */
    private HashMap<Player, Integer> updatePlayersAndCards(ArrayList<Player> players) {
        HashMap<Player, Integer> newTurnPlayerScores = new HashMap<>();
        for (Player player: players) {
            if (player != this.currentPlayer) {
                newTurnPlayerScores.put(player, player.getNumCards());
            }
        }
        return newTurnPlayerScores;
    }
}
