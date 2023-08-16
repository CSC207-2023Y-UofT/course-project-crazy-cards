package entities.card_logic;

import entities.game_logic.GameAccess;
import enums.Suit;

/**
 * This class represents a special effect that changes the current suit to a random suit.
 */
public class RandomSuitEffect implements SpecialEffect {
    private static final Suit[] suits = {Suit.SPADE, Suit.HEART, Suit.CLUB, Suit.DIAMOND};
    private final boolean alwaysPlayable;

    /**
     * Constructs a RandomSuitEffect.
     * @param alwaysPlayable Whether a card with this effect is always playable.
     */
    public RandomSuitEffect(boolean alwaysPlayable) {
        this.alwaysPlayable = alwaysPlayable;
    }

    /**
     * Changes the current suit to a random suit.
     * @param game The game to apply the effect to.
     */
    @Override
    public void action(GameAccess game) {
        Suit random = suits[(int) (Math.random() * suits.length)];
        game.setCurrentSuit(random);
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
