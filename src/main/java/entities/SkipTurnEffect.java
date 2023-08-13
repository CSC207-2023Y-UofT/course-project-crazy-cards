package entities;

public class SkipTurnEffect implements SpecialEffect {
    private boolean alwaysPlayable;

    public SkipTurnEffect(boolean alwaysPlayable) {
        this.alwaysPlayable = alwaysPlayable;
    }

    @Override
    public void action(GameAccess game) {
        game.moveNextTurn();
    }

    @Override
    public boolean getAlwaysPlayable() {
        return this.alwaysPlayable;
    }
}
