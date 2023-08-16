package entities.card_logic;

import entities.game_logic.GameAccess;
import enums.Rank;

/**
 * This class represents a special effect that changes the current rank to a random rank.
 */
public class RandomRankEffect implements SpecialEffect {
    private static final Rank[] ranks = {Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING};
    private final boolean alwaysPlayable;

    /**
     * Constructs a RandomRankEffect.
     * @param alwaysPlayable Whether a card with this effect is always playable.
     */
    public RandomRankEffect(boolean alwaysPlayable) {
        this.alwaysPlayable = alwaysPlayable;
    }

    /**
     * Changes the current rank to a random rank.
     * @param game The game to apply the effect to.
     */
    @Override
    public void action(GameAccess game) {
        Rank random = ranks[(int) (Math.random() * ranks.length)];
        game.setCurrentRank(random);
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
