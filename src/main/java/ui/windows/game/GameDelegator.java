package ui.windows.game;

/**
 * Wrapper class for user interaction delegators in a game.
 */
public class GameDelegator {
    private GamePlayDelegator playDelegator;
    private GameSkipDelegator skipDelegator;
    private GameDrawDelegator drawDelegator;

    /**
     * Construct a GameDelegator with the given delegators.
     * @param playDelegator the delegator for playing cards
     * @param skipDelegator the delegator for skipping turns
     * @param drawDelegator the delegator for drawing cards
     */
    public GameDelegator(GamePlayDelegator playDelegator, GameSkipDelegator skipDelegator, GameDrawDelegator drawDelegator) {
        this.playDelegator = playDelegator;
        this.skipDelegator = skipDelegator;
        this.drawDelegator = drawDelegator;
    }

    /**
     * Get the delegator for playing cards.
     * @return the delegator for playing cards
     */
    public GamePlayDelegator getPlayDelegator() {
        return playDelegator;
    }

    /**
     * Get the delegator for skipping turns.
     * @return the delegator for skipping turns
     */
    public GameSkipDelegator getSkipDelegator() {
        return skipDelegator;
    }

    /**
     * Get the delegator for drawing cards.
     * @return the delegator for drawing cards
     */
    public GameDrawDelegator getDrawDelegator() {
        return drawDelegator;
    }
}
