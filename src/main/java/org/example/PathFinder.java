package org.example;

import java.util.*;

public class PathFinder {
    private final Map map;
    private final Coordinates startCoordinate;
    private final Coordinates targetCoordinate;

    public PathFinder(Map map, Coordinates from, Coordinates to) {
        this.map = map;
        this.startCoordinate = from;
        this.targetCoordinate = to;
    }

    public List<Coordinates> getPath()
    {
        Stack<Coordinates> path = new Stack<>();
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        path.push(startCoordinate);
        visitedCoordinates.add(startCoordinate);
        while (!path.isEmpty() && !path.peek().equals(targetCoordinate))
        {
            Coordinates nextCoordinate = chooseCheapestNeighbour(path.peek());
            if (visitedCoordinates.add(nextCoordinate))
            {
                path.push(nextCoordinate);
            }
            else
            {
                break;
            }
        }
        return path;
    }
    private Coordinates chooseCheapestNeighbour(Coordinates node)
    {
        List<Coordinates> neighbours = new ArrayList<>(MapUtil.getNeighbours(node, map.getWidth(), map.getLength()));
        int minNodeWeight = Integer.MAX_VALUE;
        Coordinates cheapestNeighbour = node;

        for (Coordinates neighbour : neighbours)
        {
            if (map.getEntity(neighbour) == null || neighbour.equals(targetCoordinate))
            {
                int weight = evaluateCoordinate(neighbour);
                if (weight < minNodeWeight)
                {
                    minNodeWeight = weight;
                    cheapestNeighbour = neighbour;
                }
            }
        }
        return cheapestNeighbour;
    }

    private int evaluateCoordinate(Coordinates coordinate)
    {
       return MapUtil.countStepsBetweenCoordinates(startCoordinate, coordinate) +
               MapUtil.countStepsBetweenCoordinates(coordinate, targetCoordinate) * 10;
    }
}
