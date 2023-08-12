package entities;

public class ObserverUpdate implements ObserverNotifier {
    private ObservableGame game;

    public ObserverUpdate(ObservableGame game) {
        this.game = game;
    }

    @Override
    public void update() {
        game.notifyGameObservers();
    }
}
