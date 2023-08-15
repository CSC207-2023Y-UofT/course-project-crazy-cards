package entities.card_logic;

import entities.game_logic.GameAccess;

/**
 * This interface represents a special effect that can be applied to a game.
 */
public interface SpecialEffect {
    /**
     * Does this effect.
     * @param game The game to apply the effect to.
     */
    void action(GameAccess game);

    /**
     * Returns whether a card with this effect is always playable.
     * @return Whether a card with this effect is always playable.
     */
    boolean getAlwaysPlayable();
}
