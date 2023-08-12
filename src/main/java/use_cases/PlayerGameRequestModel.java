package use_cases;

import enums.Rank;
import enums.Suit;
import enums.TurnAction;

/**
 * An object containing the details of a User's request with a particular Game.
 */
public class PlayerGameRequestModel {
    private String playerName;
    private TurnAction action;
    private Suit suit;
    private Rank rank;

    /**
     * Construct a PlayerGameRequestModel object with the following information.
     * @param playerName The name of the Player whose turn it is.
     * @param suit The suit of the Card that was requested to be played.
     * @param rank The value of the Card that was requested to be played.
     * @param action The action that the User requested to take.
     */
    public PlayerGameRequestModel(String playerName, Suit suit, Rank rank,
                                  TurnAction action) {
        this.playerName = playerName;
        this.action = action;
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Get if the User requested to play a Card.
     * @return True iff requested to play a Card.
     */
    public boolean getPlayCardRequest() {
        return action == TurnAction.PLAY;
    }

    /**
     * Get if the User requested to pick up a Card from the deck.
     * @return True iff requested to pick up a Card.
     */
    public boolean getPickUpCardRequest() {
        return action == TurnAction.DRAW;
    }

    /**
     * Get whether the User requested to skip their turn.
     * @return True iff requested to skip turn.
     */
    public boolean getSkipTurnRequest() {
        return action == TurnAction.SKIP;
    }

    /**
     * Get whether the request has been made to start the Game.
     * @ True iff request to start the Game.
     */
    public boolean getStartGameRequest() {
        return action == TurnAction.START;
    }

    /**
     * Get the action that the User requested to take.
     * @return A TurnAction enum value.
     */
    public TurnAction getAction() {
        return action;
    }

    /**
     * Get the name of the current Player.
     * @return A String containing name of current Player.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Get the suit of the Card that was requested to be played.
     * This method assumes that a Card was played, and thus it should not return null.
     * @return A Suit enum value.
     */
    public Suit getCardSuit() {
        return this.suit;
    }

    /**
     * Get the value of the Card that was requested to be played.
     * This method assumes that a Card was played, and thus it should not return null.
     * @return A Rank enum value.
     */
    public Rank getCardValue(){
        return this.rank;
    }
}