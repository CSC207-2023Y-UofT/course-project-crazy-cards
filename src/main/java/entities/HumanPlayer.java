package entities;

import java.io.*;
import java.util.*;

public class HumanPlayer implements Player {

    String name;
    int numCards;
    ArrayList<Card> hand = new ArrayList<>();

    public HumanPlayer(String name, int numCards) {
        this.name = name;
        this.numCards = numCards;
    }

    public static void main(String[] args) throws IOException {
    }

    public String getStats() {
        return "";
    }

    public void incrementWins() {

    }

    public void incrementLosses() {

    }

    @Override
    public int getNumCard() {
        return this.numCards;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void putCard(Game game, Card card) {
        this.hand.remove(card);
        game.playCard(card);
    }

    @Override
    public void setHand() {

    }
}
