package entities.card_logic;

import entities.game_logic.GameAccess;

/**
 * This class represents a special effect that reverses the direction of the game.
 */
public class ReverseEffect implements SpecialEffect {
    private final boolean alwaysPlayable;

    /**
     * Constructs a ReverseEffect.
     * @param alwaysPlayable Whether a card with this effect is always playable.
     */
    public ReverseEffect(boolean alwaysPlayable) {
        this.alwaysPlayable = alwaysPlayable;
    }

    /**
     * Reverses the direction of the game.
     * @param game The game to apply the effect to.
     */
    @Override
    public void action(GameAccess game) {
        game.reverseDirection();
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
