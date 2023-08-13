package ui.factories;

import controllers.GameBridge;
import ui.windows.game.CardDelegator;
import ui.windows.game.GameController;
import ui.windows.game.GameDelegator;
import ui.windows.game.GameDisplay;
import ui.windows.game.GameDrawDelegator;
import ui.windows.game.GamePlayDelegator;
import ui.windows.game.GameSkipDelegator;
import ui.windows.game.GameWindow;
import ui.windows.game.SwitchUpdater;
import ui.windows.Window;

public class GameWindowFactory implements WindowFactory {
    private GameBridge bridge;

    public GameWindowFactory(GameBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * Using the different controllers, delegators and displays created, return a new GameWindow for
     * the game to be played using.
     * @return A new GameWindow that will display the game for the user.
     */
    @Override
    public Window createWindow() {
        GameController controller = new GameController(bridge);
        
        GamePlayDelegator playDel = new GamePlayDelegator(controller);
        GameDrawDelegator drawDel = new GameDrawDelegator(controller);
        GameSkipDelegator skipDel = new GameSkipDelegator(controller);
        GameDelegator delegator = new GameDelegator(playDel, skipDel, drawDel);

        SwitchUpdater switchUp = new SwitchUpdater(controller);

        CardDelegator cardDel = new CardDelegator(controller);

        GameDisplay display = new GameDisplay(delegator, cardDel, switchUp);
        controller.setDisplay(display); 

        return new GameWindow(display);
    }
    
}
