package use_cases;

import entities.GameObserver;
import entities.*;

import java.util.ArrayList;
import java.util.HashMap;

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
     * @param game
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
        this.currentPlayer = game.getCurrentTurn();
        this.currentCard = game.getCurrentCard();
        this.hasWinner = game.hasWinner();
        for(Player p: game.getPlayers()) {
            if(p.equals(currentPlayer)) {
                this.playersAndCards.remove(p);
            }
            else {
                this.playersAndCards.remove(p);
                this.playersAndCards.put(p, p.getNumCards());
            }
        }
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
