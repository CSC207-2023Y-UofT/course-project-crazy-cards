package entities;

public class ForceDrawEffect implements SpecialEffect {
    private int drawAmount;
    private boolean alwaysPlayable;

    public ForceDrawEffect(int drawAmount, boolean alwaysPlayable) {
        this.drawAmount = drawAmount;
        this.alwaysPlayable = alwaysPlayable;
    }

    @Override
    public void action(GameAccess game) {
        Player nextPlayer = game.getNextTurn();
        game.drawCards(drawAmount, nextPlayer);
    }

    @Override
    public boolean getAlwaysPlayable() {
        return this.alwaysPlayable;
    }
}
