package org.example.entities;

import org.example.*;

import java.util.List;

public class Mouse extends Creature {
    private int healthPoint = 150;
    private static final int DEFAULT_SPEED = 2;
    private static final int RUN_SPEED = 3;
    private static final int TRIGGER_VISION_DISTANCE = 4;
    private static final String ICON = "🐭";
    private Coordinates coordinates;

    public Mouse() {
        super(coordinates);
    }


    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getIcon() {
        return ICON;
    }

    @Override
    public void move(Map map) {
        TargetFinder targetFinder = new TargetFinder(map, coordinates);
        if (areThereCatsAround(targetFinder))
        {
            shiftCoordinates(targetFinder, map, RUN_SPEED, new Box());
        }
        else
        {
            shiftCoordinates(targetFinder, map, DEFAULT_SPEED, new MouseFood());
        }
    }

    private void shiftCoordinates(TargetFinder targetFinder, Map map, int shift, Entity target)
    {
        Coordinates targetCoordinates = targetFinder.getTargetCoordinate(target);
        PathFinder pathFinder = new PathFinder(map, coordinates, targetCoordinates);
        List<Coordinates> fullPath = pathFinder.getPath();
        map.simulationMap.put(coordinates, null);

        if (fullPath.size() > shift)
        {
            coordinates = fullPath.get(shift);
        }
        else
        {
            Coordinates entityCoordinate = fullPath.get(fullPath.size() - 1);
            coordinates = entityCoordinate;
            new Actions(map).handleEvent(map.simulationMap.get(entityCoordinate));
        }
        map.setEntity(coordinates, this);
    }

    private boolean areThereCatsAround(TargetFinder targetFinder) {
        Coordinates nearestCat = targetFinder.getTargetCoordinate(new Cat());
        return Math.abs(coordinates.x() - nearestCat.x()) <= TRIGGER_VISION_DISTANCE && Math.abs(coordinates.y() - nearestCat.y()) <= TRIGGER_VISION_DISTANCE;
    }

}
