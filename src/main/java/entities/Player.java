package entities;

import java.util.ArrayList;

public abstract class Player {
    private Hand hand;
    private String name;

    /**
     * Construct an instance of a card game Player.
     */
    public Player() {}

    /**
     * Construct an instance of a card game Player with a given Hand.
     * There are two constructors here for flexibility when writing the use case classes
     * so that we can delay the decision of constructing a player with a given Hand or setting a Hand later.
     * @param hand The Hand this player has been dealt.
     */
    public Player(String name, Hand hand) {
        this.hand = hand;
        this.name = name;
    }

    /**
     * Get the number of cards in the hand of this Player.
     * @return The number of cards in this Player's hand.
     */
    public int getNumCards() {
        ArrayList<Card> cards = this.hand.getCards();
        return cards.size();
    }

    /**
     * Place down a Card from this Player's hand on to the given Game.
     * @param game The Game instance this player puts the Card down onto.
     * @param selectedCard The Card from this Player's hand that will be placed onto the current Game.
     */
    public void playCard(Game game, Card selectedCard) {
        game.putCardDown(selectedCard);
        this.hand.removeCard(selectedCard);
    }

    /**
     * Set this Player's hand to the given Hand.
     * @param hand The Hand which will be set as the Player's Hand.
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * Get this Player's Hand.
     * @return A Hand object, representing this Player's Hand.
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * Get the cards in this Player's hand. The user can use this to see a representation of the Cards a player has.
     * @return An ArrayList containing the cards in this Player's Hand.
     */
    public ArrayList<Card> getCards() {
        return this.hand.getCards();
    }

    /**
     * Have this Player pick up a Card from the Deck of the provided Game.
     */
    public void pickUpCard(Game game) {
        Card cardToAdd = game.getGameDeck().playerPickUp();
        this.hand.addCard(cardToAdd);
        game.setCurrentTurnHasPickedUpTrue();
    }

    /**
     * Get the name of this Player.
     * @return A String containing the name of this HumanPlayer.
     */
    public String getName() {
        return this.name;
    }
}
