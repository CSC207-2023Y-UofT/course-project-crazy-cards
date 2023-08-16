package entities.card_logic;

import entities.game_logic.GameAccess;
import entities.player_logic.Player;

/**
 * This class represents a special effect that forces the next player to draw a certain amount of cards.
 */
public class ForceDrawEffect implements SpecialEffect {
    private final int drawAmount;
    private final boolean alwaysPlayable;

    /**
     * Constructs a ForceDrawEffect.
     * @param drawAmount The amount of cards to draw.
     * @param alwaysPlayable Whether a card with this effect is always playable.
     */
    public ForceDrawEffect(int drawAmount, boolean alwaysPlayable) {
        this.drawAmount = drawAmount;
        this.alwaysPlayable = alwaysPlayable;
    }

    /**
     * Forces the next player to draw a certain amount of cards.
     * @param game The game to apply the effect to.
     */
    @Override
    public void action(GameAccess game) {
        Player nextPlayer = game.getNextTurn();
        game.drawCards(drawAmount, nextPlayer);
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
