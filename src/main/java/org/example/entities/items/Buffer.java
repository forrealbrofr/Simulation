package org.example.entities.items;

import org.example.Simulation;
import org.example.entities.Entity;
import org.example.entities.Creature;

public abstract class Buffer extends Entity {
    protected int buffExpirationIteration;
    public abstract void buff(Creature creature);
    public abstract void deBuff(Creature creature);

    public boolean hasBuffExpired(Creature creature)
    {
        if (buffExpirationIteration == Simulation.currentIteration)
        {
            deBuff(creature);
            return true;
        }
        return false;
    }
}
