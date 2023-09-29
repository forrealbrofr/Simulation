package org.example;

import java.util.*;

public class PathFinder {
    private final Map map;
    private final Coordinates startCoordinate;
    private final Coordinates targetCoordinate;

    public PathFinder(Map map, Coordinates startCoordinate, Coordinates targetCoordinate) {
        this.map = map;
        this.startCoordinate = startCoordinate;
        this.targetCoordinate = targetCoordinate;
    }

    public List<Coordinates> getPath()
    {
        Stack<Coordinates> path = new Stack<>();
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        path.push(startCoordinate);
        visitedCoordinates.add(startCoordinate);
        while (!path.peek().equals(targetCoordinate))
        {
            path.push(chooseCheapestNeighbour(path.peek()));
            if (!visitedCoordinates.add(path.peek()))
            {
                path.pop();
            }
        }
        return path;
    }
    private Coordinates chooseCheapestNeighbour(Coordinates coordinate)
    {
        List<Coordinates> neighbours = new ArrayList<>(map.getNeighbours(coordinate));
        int minWeight = Integer.MAX_VALUE;
        Coordinates cheapestNeighbour = null;

        for (Coordinates neighbour : neighbours)
        {
            if (map.simulationMap.get(neighbour) == null || neighbour.equals(targetCoordinate))
            {
                int weight = evaluateCoordinate(neighbour);
                if (weight < minWeight)
                {
                    minWeight = weight;
                    cheapestNeighbour = neighbour;
                }
            }
        }
        return cheapestNeighbour;
    }

    private int evaluateCoordinate(Coordinates coordinate)
    {
       return map.countStepsBetweenCoordinates(coordinate, startCoordinate) +
               map.countStepsBetweenCoordinates(coordinate, targetCoordinate) * 10;
    }
}
