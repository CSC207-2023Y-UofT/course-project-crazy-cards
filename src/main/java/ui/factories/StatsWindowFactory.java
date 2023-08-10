package ui.factories;

import ui.windows.StatsController;
import ui.windows.StatsDelegator;
import ui.windows.StatsDisplay;
import ui.windows.StatsWindow;
import ui.windows.Window;

public class StatsWindowFactory implements WindowFactory {

    /**
     * Creates a controller, delegator and display for statistics page usage, before returning a new
     * StatsWindow using the three previously mentioned classes.
     * @return A new StatsWindow to display statistics on.
     */
    @Override
    public Window createWindow() {
        StatsController controller = new StatsController();
        StatsDelegator delegator = new StatsDelegator(controller);
        StatsDisplay display = new StatsDisplay(delegator);
        controller.setDisplay(display);

        return new StatsWindow(display);
    }
}
