package entities;

import java.util.ArrayList;

public abstract class Player {

    Hand hand;

    /**
     * Construct an instance of a card game Player.
     */
    public Player() {}

    /**
     * Construct an instance of a card game Player with a given Hand
     * There are two constructors here for now such that there is flexibility when writing the use case classes,
     *  so that we can delay the decision of constructing a player with a given Hand or setting a Hand later.
     * @param hand The Hand this player has been dealt.
     */
    public Player(Hand hand) {
        this.hand = hand;
    }

    /**
     * get the number of cards in the hand of this Player.
     * @return number of cards in this Player's hand.
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
     * @param hand The Hand which will be the Player's Hand.
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * Get the cards in this Player's hand. For use when representing the Card's a player has to the User.
     * @return An ArrayList containing the cards in this Player's Hand.
     */
    public ArrayList<Card> getCards() {
        return this.hand.getCards();
    }
}
