package entities.card_logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes parameters for adding special effects to cards.
 */
public class EffectGenerationParameters {
    private List<WeightedEffect> effectsByWeight;

    private double chance;
    private double totalWeight;
    private boolean recalculateTotalWeight;

    /**
     * Creates a parameter object.
     * @param chance The chance of a card having a special effect.
     */
    public EffectGenerationParameters(double chance) {
        this.chance = chance;
        this.effectsByWeight = new ArrayList<>();
    }

    /**
     * Adds an effect to the list of effects. 
     * Weight dictates the chance of the effect being chosen, in proportion to other effects.
     * @param effect The effect to add.
     * @param weight The weight of the effect.
     */
    public void addEffect(SpecialEffect effect, double weight) {
        this.recalculateTotalWeight = true;
        this.effectsByWeight.add(new WeightedEffect(effect, weight));
    }

    /**
     * Gets a random effect from the list of effects, with respect to weights.
     * The chance of an effect being chosen is equal to its weight divided by the total weight of all effects.
     * @return A random effect.
     */
    public SpecialEffect getRandomEffect() {
        double random = Math.random() * this.getTotalWeight();
        double currentWeight = 0;
        for (WeightedEffect weightedEffect : this.effectsByWeight) {
            currentWeight += weightedEffect.getWeight();
            if (currentWeight >= random) {
                return weightedEffect.getEffect();
            }
        }
        return null;
    }

    /**
     * Gets the total weight of stored effects.
     * @return The total weight.
     */
    public double getTotalWeight() {
        // Cache weight until it changes.
        if (!this.recalculateTotalWeight) {
            return this.totalWeight;
        }
        else {
            double totalWeight = 0;
            for (WeightedEffect effect : this.effectsByWeight) {
                totalWeight += effect.getWeight();
            }
            this.totalWeight = totalWeight;
            return totalWeight;
        }
    }

    /**
     * Gets the chance of any card having a special effect.
     * @return The chance.
     */
    public double getChance() {
        return this.chance;
    }
}

/**
 * Private wrapper class for a weighted effect.
 */
class WeightedEffect {
    private SpecialEffect effect;
    private double weight;

    /**
     * Creates a weighted effect.
     * @param effect The effect.
     * @param weight The weight.
     */
    public WeightedEffect(SpecialEffect effect, double weight) {
        this.effect = effect;
        this.weight = weight;
    }

    /**
     * Gets the effect.
     * @return The effect.
     */
    public SpecialEffect getEffect() {
        return this.effect;
    }

    /**
     * Gets the weight.
     * @return The weight.
     */
    public double getWeight() {
        return this.weight;
    }
}
