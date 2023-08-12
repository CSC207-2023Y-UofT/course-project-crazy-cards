package entities;

/**
 * Delegate for observer notification.
 * 
 * Hides observable objects' implementation from notifiers.
 */
public class ObserverNotifier implements IObserverNotifier {
    private ObservableGame game;

    public ObserverNotifier(ObservableGame game) {
        this.game = game;
    }

    @Override
    public void update() {
        game.notifyGameObservers();
    }
}
