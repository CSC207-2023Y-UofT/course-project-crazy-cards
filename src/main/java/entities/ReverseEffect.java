package entities;

public class ReverseEffect implements SpecialEffect {
    private boolean alwaysPlayable;

    public ReverseEffect(boolean alwaysPlayable) {
        this.alwaysPlayable = alwaysPlayable;
    }

    @Override
    public void action(GameAccess game) {
        game.reverseDirection();
    }

    @Override
    public boolean getAlwaysPlayable() {
        return this.alwaysPlayable;
    }
}
