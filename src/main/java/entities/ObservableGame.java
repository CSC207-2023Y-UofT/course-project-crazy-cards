package entities;

public interface ObservableGame {

    /**
     * Notify all GameObservers observing this Game that there has been a change.
     */
    void notifyGameObservers();

    /**
     * Add a GameObserver to the list of observers.
     */
    void addObserver(GameObserver observer);

    /**
     * Delete the given observer from the list of observers.
     * @param observer The GameObserver to be deleted from the list of observers.
     */
    void deleteObserver(GameObserver observer);

    /**
     * Completely clear the list of observers, such that it is now empty.
     */
    void deleteObservers();
}
