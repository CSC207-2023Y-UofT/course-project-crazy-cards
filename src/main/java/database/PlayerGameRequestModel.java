package use_cases;

/**
 * An object containing the details of a User's request with a particular Game.
 */
public class PlayerGameRequestModel {

    private boolean playCardRequest;
    private boolean pickUpCardRequest;
    private boolean skipTurnRequest;
    private String playerName;
    private String cardSuit;
    private String cardValue;

    /**
     * Construct a PlayerGameRequestModel object with the following information.
     * @param playerName The name of the Player whose turn it is.
     * @param cardValue The value of the chosen Card, could be null if none chosen.
     * @param cardSuit The suit of the chosen Card, could be null if none chosen.
     * @param playCard True iff requested to play a Card.
     * @param pickCard True iff requested to pick up a Card from the Deck.
     * @param skipTurn True iff requested to skip the current turn.
     */
    public PlayerGameRequestModel(String playerName, String cardValue, String cardSuit,
                                  boolean playCard, boolean pickCard, boolean skipTurn) {
        this.pickUpCardRequest = pickCard;
        this.playCardRequest = playCard;
        this.skipTurnRequest = skipTurn;
        this.playerName = playerName;
        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
    }

    /**
     * Get if the User requested to play a Card.
     * @return True iff requested to play a Card.
     */
    boolean getPlayCardRequest() {
        return this.playCardRequest;
    }

    /**
     * Get if the User requested to pick up a Card from the deck.
     * @return True iff requested to pick up a Card.
     */
    boolean getPickUpCardRequest() {
        return this.pickUpCardRequest;
    }

    /**
     * Get whether the User requested to skip their turn.
     * @return True iff requested to skip turn.
     */
<<<<<<< HEAD
    boolean getSkipTurnRequest() {
        return this.skipTurnRequest;
    }
=======
    boolean getSkipTurnRequest() { return this.skipTurnRequest; }
>>>>>>> 8e7484b (Created the Model and Controller for when a User is playing a Game.)

    /**
     * Get the name of the current Player.
     * @return String containing name of current Player.
     */
<<<<<<< HEAD
    String getPlayerName() {
        return this.playerName;
    }
=======
    String getPlayerName() { return this.playerName; }
>>>>>>> 8e7484b (Created the Model and Controller for when a User is playing a Game.)

    /**
     * Get the suit of the Card that was requested to be played.
     * This method assumes that a Card was played, and thus it should not return null.
     * @return a String containing the suit of the chosen Card.
     */
<<<<<<< HEAD
    String getCardSuit() {
        return this.cardSuit;
    }
=======
    String getCardSuit() { return this.cardSuit; }
>>>>>>> 8e7484b (Created the Model and Controller for when a User is playing a Game.)

    /**
     * Get the value of the Card that was requested to be played.
     * This method assumes that a Card was played, and thus it should not return null.
     * @return a String containing the value of the chosen Card.
     */
<<<<<<< HEAD
    String getCardValue(){
        return this.cardValue;
    }
=======
    String getCardValue(){ return this.cardValue; }
>>>>>>> 8e7484b (Created the Model and Controller for when a User is playing a Game.)
}
