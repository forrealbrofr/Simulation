package org.example;

import org.example.entities.Entity;

import java.util.*;

public class TargetFinder {
private final Map map;
    public TargetFinder(Map map) {
        this.map = map;
    }

    /**
     * game shouldn't allow not finding a target's coordinates
     */

    public Coordinates getTargetCoordinate(Coordinates startCoordinate,  String targetClassName)
    {
        Queue<Coordinates> queue = new ArrayDeque<>();
        Set<Coordinates> set = new HashSet<>();
        queue.add(startCoordinate);
        set.add(startCoordinate);
        while (!queue.isEmpty())
        {
            addDistinctNeighboursIntoQueue(queue, set);
            Coordinates targetCoordinates = queue.poll();
            Entity entity = map.getEntity(targetCoordinates);
            if (isInstanceOfTarget(targetClassName, entity))
            {
                return targetCoordinates;
            }
        }
        return new Coordinates(-1, -1);
    }

    private static boolean isInstanceOfTarget(String target, Entity entity) {
        return entity != null && entity.getClass().getSimpleName().equalsIgnoreCase(target);
    }

    private void addDistinctNeighboursIntoQueue(Queue<Coordinates> queue, Set<Coordinates> set) {
        List<Coordinates> neighbours = MapUtil.getNeighbours(queue.element(), map.getWidth(), map.getLength());
        for (Coordinates neighbour : neighbours) {
            if (set.add(neighbour))
            {
                queue.offer(neighbour);
            }
        }
    }
}
