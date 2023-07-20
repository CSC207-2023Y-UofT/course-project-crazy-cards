package entities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Card currentCard;
    private ArrayList<Player> players;

    private Deck gameDeck;

    private Player winner;

    private Player currentTurn;

    public Game(Deck deck, ArrayList<Player> players) {
        this.gameDeck = deck;
        this.players = new ArrayList<>(players);
    }

    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    private void changeCurrentTurn() {}

    public Card getCurrentCard() {
        return this.currentCard;
    }

    public void playCard(Card card) {
        this.gameDeck.addCardToDeck(this.currentCard);
        this.currentCard = card;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return new ArrayList<Player>(this.players);
    }

    public boolean hasWinner() {
        return this.winner instanceof Player;
    }

    public Player getWinner() {
        return this.winner;
    }
}
