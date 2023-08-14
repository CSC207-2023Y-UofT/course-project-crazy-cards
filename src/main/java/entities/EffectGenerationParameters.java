package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EffectGenerationParameters {
    private List<WeightedEffect> effectsByWeight;

    private double chance;
    private double totalWeight;
    private boolean recalculateTotalWeight;

    public EffectGenerationParameters(double chance) {
        this.chance = chance;
        this.effectsByWeight = new ArrayList<>();
    }

    public void addEffect(SpecialEffect effect, double weight) {
        this.recalculateTotalWeight = true;
        this.effectsByWeight.add(new WeightedEffect(effect, weight));
    }

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

    public double getTotalWeight() {
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

    public double getChance() {
        return this.chance;
    }
}

class WeightedEffect {
    private SpecialEffect effect;
    private double weight;

    public WeightedEffect(SpecialEffect effect, double weight) {
        this.effect = effect;
        this.weight = weight;
    }

    public SpecialEffect getEffect() {
        return this.effect;
    }

    public double getWeight() {
        return this.weight;
    }
}
