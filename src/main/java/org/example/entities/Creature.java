package org.example.entities;

import org.example.*;

import java.util.List;

public abstract class Creature extends Entity {
    private final Coordinates coordinates;

    public Creature(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public abstract void move(Map map);
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
}
