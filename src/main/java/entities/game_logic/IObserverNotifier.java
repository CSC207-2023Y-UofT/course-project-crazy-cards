package entities.game_logic;

/**
 * Interface to be implemented whenever an ObserverNotifier is created, to let observers know to update. This way, the
 * implementation of the observable objects is hidden from any notifiers.
 */
public interface IObserverNotifier {
    /**
     * Notifies any observers to update.
     */
    void update();
}
