package use_cases;

import entities.GameObserver;
import entities.*;

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
    private ArrayList<Card> currentPlayerCards;
    private HashMap<Player, Integer> playersAndCards = new HashMap<>();
    private boolean hasWinner;

    /**
     * Given a Game, construct a GameState objects which keep track of the Game's current Player,
     * current Card, their Cards, the other players and the number of Cards they have,
     * as well as if the Game has a winner.
     * @param game The Game containing the information to update this GameState.
     */
    public GameState(ObservableGame game) {

        this.currentPlayer = game.getCurrentTurn();
        this.currentCard = game.getCurrentCard();
        for (Player player: game.getPlayers()) {
            if(player != currentPlayer) {
                this.playersAndCards.put(player, player.getNumCards());
            }
        }
        this.hasWinner = game.hasWinner();
        this.currentPlayerCards = currentPlayer.getCards();

    }

    /**
     * Update this GameState given the ObservableGame.
     * @param game An ObservableGame that has notified this GameObserver of an update.
     */
    @Override
    public void updateGameObserver(ObservableGame game) {
        // Add the Player whose turn it was previous to the HashMap and remove the new current Player from it.
        Player prevPlayer = this.currentPlayer;
        this.playersAndCards.put(prevPlayer, prevPlayer.getNumCards());
        this.currentPlayer = game.getCurrentTurn();
        this.playersAndCards.remove(this.currentPlayer);
        this.currentCard = game.getCurrentCard();
        this.hasWinner = game.hasWinner();
        this.currentPlayerCards = this.currentPlayer.getCards();
    }

    /**
     * Get the current Player of the Game.
     * @return a Player representing the current turn of the Game.
     */
   public Player getCurrentPlayer() {
        return this.currentPlayer;
   }

    /**
     * Get the last card played in the Game.
     * @return the current Card in the Game, (last played Card).
     */
   public Card getCurrentCard() {
        return this.currentCard;
   }

    /**
     * Get the Cards of the current Player.
     * @return A new ArrayList of the Cards belonging to the current Player.
     */
   public ArrayList<Card> getCurrentPlayerCards() {
        return new ArrayList<>(currentPlayerCards);
   }

    /**
     * Get whether the Game this GameState is observing has a winner.
     * @return True iff the Game has a winner.
     */
   public boolean getHasWinner() {
       return this.hasWinner;
   }

    /**
     * Get the Players beside the current Player and their number of Cards.
     * @return A HashMap with keys being the other Players, and values being their number of cards.
     */
    public HashMap<Player, Integer> getPlayersAndCards() {
        return playersAndCards;
    }
}