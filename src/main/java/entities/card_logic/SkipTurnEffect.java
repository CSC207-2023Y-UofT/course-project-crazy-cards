package entities.card_logic;

import entities.game_logic.GameAccess;

/**
 * This class represents a special effect that skips the next player's turn.
 */
public class SkipTurnEffect implements SpecialEffect {
    private boolean alwaysPlayable;

    /**
     * Constructs a SkipTurnEffect.
     * @param alwaysPlayable Whether a card with this effect is always playable.
     */
    public SkipTurnEffect(boolean alwaysPlayable) {
        this.alwaysPlayable = alwaysPlayable;
    }

    /**
     * Skips the next player's turn.
     * @param game The game to apply the effect to.
     */
    @Override
    public void action(GameAccess game) {
        game.moveNextTurn();
    }

    /**
     * Returns whether a card with this effect is always playable.
     * @return Whether a card with this effect is always playable.
     */
    @Override
    public boolean getAlwaysPlayable() {
        return this.alwaysPlayable;
    }
}
