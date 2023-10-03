package org.example.entities.items;

import org.example.Simulation;
import org.example.entities.Creature;
import org.example.entities.dynamicObjects.Mouse;

public class InvisibilityBuffer extends Buffer {
    public static final String ICON = "✨";
    private final static int BONUS_HEALTH_POINTS = 20;
    private final static int BUFF_DURATION = 1; //iterations
    @Override
    public String getIcon() {
        return ICON;
    }

    @Override
    public void buff(Creature creature) {
        creature.increaseHealthPoints(BONUS_HEALTH_POINTS);
        Mouse mouse = (Mouse) creature;
        mouse.setInVisible();
        buffExpirationIteration = Simulation.currentIteration + BUFF_DURATION;
    }
    @Override
    public void deBuff(Creature creature){
        Mouse mouse = (Mouse) creature;
        mouse.setVisible();
    }
}
