package ui.factories;

import ui.windows.CardDelegator;
import ui.windows.GameController;
import ui.windows.GameDelegator;
import ui.windows.GameDisplay;
import ui.windows.GameDrawDelegator;
import ui.windows.GamePlayDelegator;
import ui.windows.GameSkipDelegator;
import ui.windows.GameWindow;
import ui.windows.Window;

public class GameWindowFactory implements WindowFactory {

    /**
     * Using the different controllers, delegators and displays created, return a new GameWindow for
     * the game to be played using.
     * @return A new GameWindow that will display the game for the user.
     */
    @Override
    public Window createWindow() {
        GameController controller = new GameController();
        
        GamePlayDelegator playDel = new GamePlayDelegator(controller);
        GameDrawDelegator drawDel = new GameDrawDelegator(controller);
        GameSkipDelegator skipDel = new GameSkipDelegator(controller);
        GameDelegator delegator = new GameDelegator(playDel, skipDel, drawDel);

        CardDelegator cardDel = new CardDelegator(controller);

        GameDisplay display = new GameDisplay(delegator, cardDel);
        controller.setDisplay(display); 

        return new GameWindow(display);
    }
    
}
