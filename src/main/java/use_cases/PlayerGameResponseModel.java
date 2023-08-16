package use_cases;

import entities.card_logic.Card;
import entities.player_logic.Player;
import enums.TurnAction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is created as a response to user input. It delivers all the data needed for the controller
 * to show to the view without using any of the entity data (prevent crossover of entity code in controller layer).
 */
public class PlayerGameResponseModel {
    private String currentPlayerName;
    private boolean hasWinner;
    private HashMap<String, Integer> playersAndNumCards = new HashMap<>();
    private CardResponseModel currentCard;
    private CardResponseModel currentDrawnCard;
    private ArrayList<CardResponseModel> playerCards = new ArrayList<>();
    private TurnAction lastMove;
    private boolean success;

    /**
     * Create a new PlayerGameResponseModel, using the updated GameState provided.
     * Cards are represented as an array of string by {value, suit}.
     * @param gameState The GameState which provides the information for this ResponseModel.
     */
    public PlayerGameResponseModel(GameState gameState) {
        this.currentPlayerName = gameState.getCurrentPlayer().getName();
        this.hasWinner = gameState.getHasWinner();
        this.currentCard = cardToModel(gameState.getCurrentCard());
        this.currentDrawnCard = cardToModel(gameState.getCurrentDrawnCard());
        for(Card card: gameState.getCurrentPlayerCards()) {
            this.playerCards.add(cardToModel(card));
        }
        for(Player player: gameState.getPlayersAndCards().keySet()) {
            this.playersAndNumCards.put(player.getName(), player.getNumCards());
        }
        this.lastMove = gameState.getLastRequest();
        this.success = gameState.getLastRequestSuccess();
    }

    /**
     * Convert the given Card to an array of strings.
     * @param card The Card to be converted.
     * @return An array of Strings representing the Card as {value, suit}.
     */
    private CardResponseModel cardToModel(Card card) {
        if (card == null) {
            return null;
        }
        return new CardResponseModel(card.getSuit(), card.getRank());
    }

    /**
     * Get if this ResponseModel shows if there is a Winner.
     * @return True iff hasWinner is true.
     */
    public boolean getHasWinner() {
        return this.hasWinner;
    }

    /**
     * Get the cards of the current Player in String format.
     * @return An ArrayList of Cards in String format.
     */
    public ArrayList<CardResponseModel> getPlayerCards() {
        return playerCards;
    }

    /**
     * Get all the Players names (other than the current Player) and their respective number of Cards.
     * @return A HashMap with Player names as Strings and number of Cards as an Integer.
     */
    public HashMap<String, Integer> getPlayersAndNumCards() {
        return playersAndNumCards;
    }

    /**
     * Get the name of the current Player.
     * @return A string containing the name of the current Player.
     */
    public String getCurrentPlayerName() {
        return this.currentPlayerName;
    }

    /**
     * Get the current Card this ResponseModel is sending.
     * @return The current Card in String format.
     */
    public CardResponseModel getCurrentCard() {
        return this.currentCard;
    }

    /**
     * Get the current Drawn Card this ResponseModel is sending.
     * @return The current Drawn Card in String format.
     */
    public CardResponseModel getCurrentDrawnCard() {
        return this.currentDrawnCard;
    }

    /**
     * Get the last move made by the current Player.
     * @return The last move made by the current Player.
     */
    public TurnAction getLastMove() {
        return lastMove;
    }

    /**
     * Get if the last move made by the current Player was successful.
     * @return True iff the last move made by the current Player was successful.
     */
    public boolean getSuccess() {
        return success;
    }
}