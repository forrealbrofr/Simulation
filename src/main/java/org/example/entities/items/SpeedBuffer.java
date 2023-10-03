package org.example.entities.items;

import org.example.entities.Creature;

public class SpeedBuffer extends Buffer {
    private final static String ICON = "🌀";
    private final static int BUFF_DURATION = 2;
    private final static int SPEED_MULTIPLIER = 2;
    @Override
    public String getIcon() {
        return ICON;
    }

    @Override
    public void buff(Creature creature) {
        creature.buffSpeed(SPEED_MULTIPLIER);
        buffExpirationIteration += BUFF_DURATION;
    }

    @Override
    public void deBuff(Creature creature) {
        creature.nerfSpeed(SPEED_MULTIPLIER);
    }
}
