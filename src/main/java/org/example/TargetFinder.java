package org.example;

import org.example.entities.Entity;

import java.util.*;

public class TargetFinder {
private final Map map;
private final Coordinates startCoordinate;

    public TargetFinder(Map map, Coordinates startCoordinate) {
        this.map = map;
        this.startCoordinate = startCoordinate;
    }
    public Coordinates getTargetCoordinate(String target)
    {
        Queue<Coordinates> queue = new ArrayDeque<>();
        Set<Coordinates> set = new HashSet<>();
        queue.add(startCoordinate);
        set.add(startCoordinate);
        while (!queue.isEmpty())
        {
            addDistinctNeighboursIntoQueue(queue, set);
            queue.poll();
            if (isInstanceOf(target, queue))
            {
                return queue.element();
            }
        }
        return new Coordinates(-1, -1);
    }

    private void addDistinctNeighboursIntoQueue(Queue<Coordinates> queue, Set<Coordinates> set) {
        List<Coordinates> neighbours = map.getNeighbours(queue.element());
        for (Coordinates neighbour : neighbours) {
            if (set.add(neighbour))
            {
                queue.offer(neighbour);
            }
        }
    }
    private boolean isInstanceOf(String soughtEntity, Queue<Coordinates> queue) {
        Entity entity = map.simulationMap.get(queue.element());
        if (entity != null)
        {
            return entity.getClass().getSimpleName().equals(soughtEntity);
        }
        return false;
    }
}
