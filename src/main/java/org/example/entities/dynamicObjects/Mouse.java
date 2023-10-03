package org.example.entities.dynamicObjects;

import org.example.*;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.entities.items.Buffer;
import org.example.entities.items.MouseFood;

public class Mouse extends Creature {
    private String ICON = "🐭";
    private static final int STARTER_HEALTH_POINTS = 200;
    private static final int DEFAULT_SPEED = 2;
    private static final int RUN_SPEED = 4;
    private static final int TRIGGER_VISION_DISTANCE = 3;
    private static final int VIABILITY = 3;

    private boolean isVisible = true;

    public void setInVisible() {
        ICON = "❔";
        isVisible = false;
    }
    public void setVisible()
    {
        ICON = "🐭";
        isVisible = true;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Mouse(Coordinates coordinates)
    {
        super(coordinates, VIABILITY, STARTER_HEALTH_POINTS, DEFAULT_SPEED, RUN_SPEED);
    }

    @Override
    public String getIcon() {
        return ICON;
    }
    @Override
    public void move(Map map) {
        super.handleHungerState();
        if (super.isCreatureDead(map)) return;
        
        Coordinates nearestCat = findCats(map);
        Coordinates targetCoordinates;
        if (areThereCatsAround(nearestCat))
        {
            isTriggered = true;
            targetCoordinates = choosePlaceWhereToRun(map, nearestCat);
        }
        else
        {
            isTriggered = false;
            targetCoordinates = choosePlaceWhereToWalk(map);
        }
        super.makeMove(map, targetCoordinates);
    }


    @Override
    protected Coordinates choosePlaceWhereToRun(Map map, Coordinates nearestCat) {
        TargetFinder targetFinder = new TargetFinder(map);
        Coordinates nearestBox = targetFinder.getTargetCoordinate(currentCoordinates, "InvisibilityBuffer");
        Coordinates nearestSpeedBuffer = targetFinder.getTargetCoordinate(currentCoordinates, "SpeedBuffer");
        int stepsToNearestBox = MapUtil.countStepsBetweenCoordinates(currentCoordinates, nearestBox);
        int stepsToNearestSpeedBuffer = MapUtil.countStepsBetweenCoordinates(currentCoordinates, nearestSpeedBuffer);
        if (stepsToNearestBox <= stepsToNearestSpeedBuffer)
            if (stepsToNearestBox == stepsToNearestSpeedBuffer)
            {
                return MapUtil.countStepsBetweenCoordinates(nearestBox, nearestCat) >=
                        MapUtil.countStepsBetweenCoordinates(nearestSpeedBuffer, nearestCat)
                        ? nearestBox : nearestSpeedBuffer;
            }
            else
            {
                return nearestBox;
            }
        else
            return nearestSpeedBuffer;
    }

    @Override
    public void handleReceivedItem(Entity entity) {
        if (entity instanceof MouseFood food)
        {
            food.giveHeathPoints(this);
            iterationsWithoutFoodCount = 0;
        }
        else if (entity instanceof Buffer buffer)
        {
            buffer.buff(this);
            currentBuffs.add(buffer);
        }
    }
    private boolean areThereCatsAround(Coordinates nearestCat)
    {
        return MapUtil.countStepsBetweenCoordinates(currentCoordinates, nearestCat) <= TRIGGER_VISION_DISTANCE;
    }
    private Coordinates findCats(Map map)
    {
        TargetFinder targetFinder = new TargetFinder(map);
        return targetFinder.getTargetCoordinate(currentCoordinates, "Cat");
    }
    @Override
    protected Coordinates choosePlaceWhereToWalk(Map map)
    {
        var targetFinder = new TargetFinder(map);
        return targetFinder.getTargetCoordinate(currentCoordinates, "MouseFood");
    }
}
