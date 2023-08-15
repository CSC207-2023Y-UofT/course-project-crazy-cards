package ui.factories;

import ui.windows.game.CardDelegator;
import ui.windows.game.GameDelegator;
import ui.windows.game.GameDisplay;
import ui.windows.game.GameDrawDelegator;
import ui.windows.game.GamePlayDelegator;
import ui.windows.game.GameSkipDelegator;
import ui.windows.game.GameWindow;
import ui.windows.game.SwitchUpdater;
import controllers.interfaces.GameBridge;
import ui.windows.Window;

public class GameWindowFactory implements WindowFactory {
    private GameBridge bridge;

    /**
     * Constructor that sets the factory's GameBridge.
     * @param bridge The GameBridge to be used for the GameWindow.
     */
    public GameWindowFactory(GameBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * Using the different controllers, delegators and displays created, return a new GameWindow for
     * the game to be played on.
     * @return The new GameWindow that will display the game for the user.
     */
    @Override
    public Window createWindow() {        
        GamePlayDelegator playDel = new GamePlayDelegator(bridge);
        GameDrawDelegator drawDel = new GameDrawDelegator(bridge);
        GameSkipDelegator skipDel = new GameSkipDelegator(bridge);
        GameDelegator delegator = new GameDelegator(playDel, skipDel, drawDel);

        SwitchUpdater switchUp = new SwitchUpdater(bridge);

        CardDelegator cardDel = new CardDelegator(bridge);

        GameDisplay display = new GameDisplay(delegator, cardDel, switchUp);
        bridge.setUI(display); 

        return new GameWindow(display);
    }
    
}
