package ui.factories;

import ui.windows.StatsController;
import ui.windows.StatsDelegator;
import ui.windows.StatsDisplay;
import ui.windows.StatsWindow;
import ui.windows.Window;
import use_cases.DataAccess;

public class StatsWindowFactory implements WindowFactory {

    /**
     * Creates a controller, delegator and display for statistics page usage, before returning a new
     * StatsWindow using the three previously mentioned classes.
     * @return A new StatsWindow to display statistics on.
     */
    @Override
    public Window createWindow(DataAccess database) {
        StatsController controller = new StatsController(database);
        StatsDelegator delegator = new StatsDelegator(controller);
        StatsDisplay display = new StatsDisplay(delegator);
        controller.setDisplay(display);

        return new StatsWindow(display);
    }
}
