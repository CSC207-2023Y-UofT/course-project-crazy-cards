package ui.windows;

import enums.WindowName;

/**
 * An event that stores swapping information.
 */
public class SwapEvent {
    private WindowName previous;
    private WindowName next;

    /**
     * Construct a new SwapEvent given the previous and next window names.
     * @param previous The name of the last window.
     * @param next The name of the window being swapped to.
     */
    public SwapEvent(WindowName previous, WindowName next) {
        this.previous = previous;
        this.next = next;
    }

    /**
     * Getter method for the previous window name.
     * @return The name of the previous window.
     */
    public WindowName getPrevious() {
        return previous;
    }

    /**
     * Getter method for the next window name.
     * @return The name of the next window.
     */
    public WindowName getNext() {
        return next;
    }
}
