package entities;

import enums.Rank;

public class RandomRankEffect implements SpecialEffect {
    private Rank[] ranks = {Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING};
    private boolean alwaysPlayable;

    public RandomRankEffect(boolean alwaysPlayable) {
        this.alwaysPlayable = alwaysPlayable;
    }

    @Override
    public void action(GameAccess game) {
        Rank random = ranks[(int) (Math.random() * ranks.length)];
        game.setCurrentRank(random);
    }

    @Override
    public boolean getAlwaysPlayable() {
        return this.alwaysPlayable;
    }
}
