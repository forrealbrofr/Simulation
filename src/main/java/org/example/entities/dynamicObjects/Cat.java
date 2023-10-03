package org.example.entities.dynamicObjects;

import org.example.*;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.entities.items.CatFood;
import org.example.entities.items.SpeedBuffer;

public class Cat extends Creature {
    private static final String ICON = "😼";
    private static final int STARTER_HEALTH_POINTS = 300;
    private static final int DEFAULT_SPEED = 2;
    private static final int RUN_SPEED = 4;
    private static final int TRIGGER_VISION_DISTANCE = 3;
    private static final int VIABILITY = 3;
    private static final int EATING_MOUSE_HEALTH_POINTS = 100;

    public Cat(Coordinates coordinates) {
        super(coordinates, VIABILITY, STARTER_HEALTH_POINTS,
                DEFAULT_SPEED, RUN_SPEED);
    }

    @Override
    public void move(Map map) {
        super.handleHungerState();
        if (super.isCreatureDead(map)) return;
        Coordinates nearestMouse = findMouse(map);
        Coordinates targetCoordinates;
        if (areThereVisibleMousesAround(nearestMouse, map))
        {
            isTriggered = true;
            targetCoordinates = choosePlaceWhereToRun(map, nearestMouse);
        }
        else
        {
            isTriggered = false;
            targetCoordinates = choosePlaceWhereToWalk(map);
        }
        super.makeMove(map, targetCoordinates);
    }

    @Override
    protected Coordinates choosePlaceWhereToRun(Map map, Coordinates nearestMouse) {
        TargetFinder targetFinder = new TargetFinder(map);
        Coordinates nearestSpeedBuffer = targetFinder.getTargetCoordinate(currentCoordinates, "SpeedBuffer");
        int stepsToSpeedBuffer = MapUtil.countStepsBetweenCoordinates(currentCoordinates, nearestSpeedBuffer);
        return stepsToSpeedBuffer == 1 ? nearestSpeedBuffer : nearestMouse;
    }

    @Override
    public void handleReceivedItem(Entity entity) {
        if (entity instanceof Mouse)
        {
            increaseHealthPoints(EATING_MOUSE_HEALTH_POINTS);
            super.iterationsWithoutFoodCount = 0;
        }
        else if (entity instanceof CatFood food)
        {
            food.giveHeathPoints(this);
            super.iterationsWithoutFoodCount = 0;
        }
        else if (entity instanceof SpeedBuffer buffer)
        {
            buffer.buff(this);
            currentBuffs.add(buffer);
        }
    }
    private boolean areThereVisibleMousesAround(Coordinates nearestMouse, Map map) {
        Mouse mouse = (Mouse) map.getEntity(nearestMouse);
        if (mouse == null)
            return false;
        if (mouse.isVisible())
        {
            return MapUtil.countStepsBetweenCoordinates(nearestMouse, currentCoordinates) <= TRIGGER_VISION_DISTANCE;
        }
        return false;
    }

    public Coordinates findMouse(Map map)
    {
        TargetFinder targetFinder = new TargetFinder(map);
        return targetFinder.getTargetCoordinate(currentCoordinates, "Mouse");
    }
    protected Coordinates choosePlaceWhereToWalk(Map map)
    {
        TargetFinder targetFinder = new TargetFinder(map);
        return targetFinder.getTargetCoordinate(currentCoordinates, "CatFood");
    }

    @Override
    public String getIcon() {
        return ICON;
    }
}
