package entities;

import enums.Suit;

public class RandomSuitEffect implements SpecialEffect {
    private Suit[] suits = {Suit.SPADE, Suit.HEART, Suit.CLUB, Suit.DIAMOND};
    private boolean alwaysPlayable;

    public RandomSuitEffect(boolean alwaysPlayable) {
        this.alwaysPlayable = alwaysPlayable;
    }

    @Override
    public void action(GameAccess game) {
        Suit random = suits[(int) (Math.random() * suits.length)];
        game.setCurrentSuit(random);
    }

    @Override
    public boolean getAlwaysPlayable() {
        return this.alwaysPlayable;
    }
}
