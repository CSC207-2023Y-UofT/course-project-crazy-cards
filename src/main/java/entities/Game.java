package entities;

import java.util.List;

public class Game {
    private Card currentCard;
    private List<Player> players;

    private Deck gameDeck;

    private Player winner;

    private Player currentTurn;

    public Game(Deck deck, List<Player> players) {
        this.gameDeck = deck;
        this.players = new List<Player>(players);
    }

    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    public Card getCurrentCard() {
        return this.currentCard;
    }

    public void playCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return new List<Player>(this.players);
    }

    public boolean hasWinner() {
        return this.winner instanceof Player;
    }

    public Player getWinner() {
        return this.winner;
    }
}
