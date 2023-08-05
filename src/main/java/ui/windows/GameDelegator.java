package ui.windows;

public class GameDelegator {
    private GamePlayDelegator playDelegator;
    private GameSkipDelegator skipDelegator;
    private GameDrawDelegator drawDelegator;

    public GameDelegator(GamePlayDelegator playDelegator, GameSkipDelegator skipDelegator, GameDrawDelegator drawDelegator) {
        this.playDelegator = playDelegator;
        this.skipDelegator = skipDelegator;
        this.drawDelegator = drawDelegator;
    }

    public GamePlayDelegator getPlayDelegator() {
        return playDelegator;
    }

    public GameSkipDelegator getSkipDelegator() {
        return skipDelegator;
    }

    public GameDrawDelegator getDrawDelegator() {
        return drawDelegator;
    }
}
