package entities.game_logic;

/**
 * Delegate for observer notification.
 * Hides observable objects' implementation from notifiers.
 */
public class ObserverNotifier implements IObserverNotifier {
    private ObservableGame game;

    /**
     * Constructor for the ObserverNotifier class, which notifies any game observers to update the game.
     * @param game The current game connected to this ObserverNotifier.
     */
    public ObserverNotifier(ObservableGame game) {
        this.game = game;
    }

    /**
     * Method that notifies any observers to the current game to update.
     */
    @Override
    public void update() {
        game.notifyGameObservers();
    }
}
