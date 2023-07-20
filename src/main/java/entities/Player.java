package entities;

import java.util.ArrayList;

public abstract class Player {

    Hand hand;

    public Player() {}
    public Player(Hand hand) {
        this.hand = hand;
    }
    public int getNumCards() {
        ArrayList<Card> cards = this.hand.getCards();
        return cards.size();
    }


    public void putCard(Game game, Card selectedCard) {
        game.playCard(selectedCard);
        this.hand.removeCard(selectedCard);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getCards() {
        return this.hand.getCards();
    }
}
