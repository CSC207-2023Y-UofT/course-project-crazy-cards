package ui.windows;

/**
 * An interface for listening to window swaps.
 */
public interface SwapListener {
    /**
     * Called before a window swap.
     * @param event The event containing the window swap information.
     */
    void OnPreSwap(SwapEvent event);
}
