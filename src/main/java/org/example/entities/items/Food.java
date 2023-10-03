package org.example.entities.items;

import org.example.entities.Entity;
import org.example.entities.Creature;

public abstract class Food extends Entity {
    private final int bonusHealthPoints;

    protected Food(int bonusHealthPoints) {
        this.bonusHealthPoints = bonusHealthPoints;
    }
    public void giveHeathPoints(Creature creature)
    {
        creature.increaseHealthPoints(bonusHealthPoints);
    }
}
