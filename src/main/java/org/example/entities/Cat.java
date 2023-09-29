package org.example.entities;

import org.example.*;

import java.util.List;

public class Cat extends Creature {
    private int healthPoint = 300;
    private static final String ICON = "😼";
    private static final int DEFAULT_SPEED = 3;
    private static final int RUN_SPEED = 4;
    private static final int TRIGGER_VISION_DISTANCE = 6;
    private Coordinates coordinates;

    public Cat(Coordinates coordinates) {
        super(coordinates);
    }
    public Cat()
    {
        super(null);
    }



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
        if (areThereMousesAround(targetFinder))
        {
            shiftCoordinates(targetFinder, map, RUN_SPEED, new Mouse());
        }
        else
        {
            shiftCoordinates(targetFinder, map, DEFAULT_SPEED, new CatFood());
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



    private boolean areThereMousesAround(TargetFinder targetFinder) {
        Coordinates nearestMouse = targetFinder.getTargetCoordinate(new Mouse());
        return Math.abs(nearestMouse.x() - coordinates.x()) <= TRIGGER_VISION_DISTANCE &&
                Math.abs(nearestMouse.y() - coordinates.y()) <= TRIGGER_VISION_DISTANCE;
    }
}
