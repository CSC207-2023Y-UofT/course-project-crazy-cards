package ui.factories;

import ui.windows.StatsController;
import ui.windows.StatsDelegator;
import ui.windows.StatsDisplay;
import ui.windows.StatsWindow;
import ui.windows.Window;

public class StatsWindowFactory implements WindowFactory {
    
    @Override
    public Window createWindow() {
        StatsController controller = new StatsController();
        StatsDelegator delegator = new StatsDelegator(controller);
        StatsDisplay display = new StatsDisplay(delegator);
        controller.setDisplay(display);

        return new StatsWindow(display);
    }
    
}
