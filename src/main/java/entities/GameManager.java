package entities;

import java.util.ArrayList;

public class GameManager implements CreationAccess,
                                    GameAccess,
                                    ObservableGame {

    private Game game;
    private ArrayList<GameObserver> observers;

    public GameManager() {
        this.game = null;
    }

    @Override
    public void notifyGameObservers() {
        for (GameObserver observer : observers) {
            observer.updateGameObserver(this);
        }
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void deleteObservers() {
        observers.clear();
    }

    @Override
    public Player getCurrentTurn() {
        return getGame().getCurrentTurn();
    }

    @Override
    public Card getCurrentCard() {
        return getGame().getCurrentCard();
    }

    @Override
    public Card getDrawnCard() {
        return getGame().getDrawnCard();
    }

    @Override
    public void changeCurrentTurn() {
        getGame().changeCurrentTurn();
    }

    @Override
    public void setWinner(Player winner) {
        getGame().setWinner(winner);
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return getGame().getPlayers();
    }

    @Override
    public boolean hasWinner() {
        return getGame().hasWinner();
    }

    @Override
    public boolean isValidCard(Card card) {
        return getGame().isValidCard(card);
    }

    @Override
    public boolean getCurrentTurnHasPickedUp() {
        return getGame().getCurrentTurnHasPickedUp();
    }

    @Override
    public void playCard(Player player, Card card) {
        player.playCard(getGame(), card);
    }

    @Override
    public void pickUpCard(Player player) {
        player.pickUpCard(getGame());
    }

    @Override
    public Card selectRandomValidCard(ComputerPlayer player) {
        return player.selectRandomValidCard(getGame());
    }

    @Override
    public void buildGame(ArrayList<Player> players, Deck deck) {
        Game game = new Game(deck, players);
        Card firstCard = deck.removeCardFromDeck();
        game.putCardDown(firstCard);
    }

    private Game getGame() {
        if (game == null) {
            throw new IllegalStateException("Game has not been built yet");
        }
        return game;
    }
}
