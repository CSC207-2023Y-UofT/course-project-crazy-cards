package ui.windows;

/**
 * The interface used to implement functions within the CardLayoutManager class, which will be used
 * to manage the transitions between windows when the program contents are being displayed.
 */
public interface ICardLayoutManager {
    /**
     * Set the given pane to as the one being currently viewed by the user upon calling the method.
     * @param pane The pane passed through as the argument to be set as the current viewing pane.
     */
    void setPane(String pane);

    /**
     * Adds the given pane to the internal layout manager upon calling the method.
     * @param window The window being passed into the layout manager to be displayed.
     */
    void addPane(Window window);
}
