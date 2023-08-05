package use_cases;

import entities.Card;
import entities.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is created as a response to user input. It delivers all the data needed for the controller
 * to show to the view without using any of the entity data (prevent crossover of entity code in controller layer).
 */
public class PlayerGameResponseModel {
    private String currentPlayerName;
    private boolean hasWinner;
    private ArrayList<String[]> playerCards = new ArrayList<>();
    private HashMap<String, Integer> playersAndNumCards = new HashMap<>();
    private String[] currentCard;

    /**
     * Create a new PlayerGameResponseModel, using the updated GameState provided.
     * Cards are represented as an array of string by {value, suit}
     * @param gameState The GameState which provides the information for this ResponseModel.
     */
    public PlayerGameResponseModel(GameState gameState) {
        this.currentPlayerName = gameState.getCurrentPlayer().getName();
        this.hasWinner = gameState.getHasWinner();
        this.currentCard = cardToArray(gameState.getCurrentCard());
        for(Card card: gameState.getCurrentPlayerCards()) {
            this.playerCards.add(cardToArray(card));
        }
        for(Player player: gameState.getPlayersAndCards().keySet()) {
            this.playersAndNumCards.put(player.getName(), player.getNumCards());
        }

    }

    /**
     * Convert the given Card to an array of strings.
<<<<<<< HEAD
     * @param card The Card ot be converted.
=======
     * @param card The Card ot be converted/
>>>>>>> 8e7484b (Created the Model and Controller for when a User is playing a Game.)
     * @return An array of Strings representing the Card as {value, suit}.
     */
    private String[] cardToArray(Card card) {
        return new String[]{card.getValue(), card.getSuit()};
    }

    /**
     * Get if this ResponseModel shows if there is a Winner.
     * @return True iff hasWinner is true.
     */
    public boolean getHasWinner() { return this.hasWinner; }

    /**
     * Get the cards of the current Player in String format.
     * @return An ArrayList of Cards in String format.
     */
    public ArrayList<String[]> getPlayerCards() {
        return playerCards;
    }

    /**
     * Get all the Players names (besides current Player) and their number of Cards.
     * @return HashMap with Player names as Strings and number of Cards as an Integer.
     */
    public HashMap<String, Integer> getPlayersAndNumCards() {
        return playersAndNumCards;
    }

    /**
     * Get the name of the current Player.
     * @return A string containing the name of the current Player.
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public String getCurrentPlayerName() {
        return this.currentPlayerName;
    }
=======
    public String getCurrentPlayerName() { return this.currentPlayerName; }
>>>>>>> 8e7484b (Created the Model and Controller for when a User is playing a Game.)
=======
    public String getCurrentPlayerName() {
        return this.currentPlayerName;
    }
>>>>>>> a548eb8 (Made all getter methods to three lines instead of 1.)

    /**
     * Get the current Card this ResponseModel is sending.
     * @return The current Card in String format.
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public String[] getCurrentCard() {
        return this.currentCard;
    }
=======
    public String[] getCurrentCard() { return this.currentCard; }
>>>>>>> 8e7484b (Created the Model and Controller for when a User is playing a Game.)
=======
    public String[] getCurrentCard() {
        return this.currentCard;
    }
>>>>>>> a548eb8 (Made all getter methods to three lines instead of 1.)
}
