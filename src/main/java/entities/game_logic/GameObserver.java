package entities.game_logic;

public interface GameObserver {

    /**
     * Update this GameObserver observing the given ObservableGame.
     * @param game An ObservableGame that has notified this GameObserver of an update.
     */
    void updateGameObserver(GameAccess game);

}