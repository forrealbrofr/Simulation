package org.example;

import org.example.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public final class MapUtil {
    private MapUtil() {

    }
    public static int countStepsBetweenCoordinates(Coordinates from, Coordinates to) {
        int stepsCount = 0;
        int xDifference = Math.abs(from.x() - to.x());
        int yDifference = Math.abs(from.y() - to.y());
        while (yDifference > 0 || xDifference > 0)
        {
            int xFactor = Integer.compare(xDifference, 0);
            int yFactor = Integer.compare(yDifference, 0);
            xDifference -= xFactor;
            yDifference -= yFactor;
            stepsCount ++;
        }
        return stepsCount;
    }
    public static List<Coordinates> getNeighbours(Coordinates coordinate, int mapWidth, int mapLength)
    {
        int x = coordinate.x();
        int y = coordinate.y();
        int[][] allPossibleCoordinates = {{x + 1, y}, {x - 1, y}, {x, y - 1}, {x, y + 1},
                {x + 1, y + 1}, {x + 1,  y - 1},  {x - 1, y - 1}, {x - 1, y + 1} };
        int[][] mapModel = new int[mapWidth][mapLength];
        List<Coordinates> neighbours = new ArrayList<>();
        for (int[] neighbour : allPossibleCoordinates) {
            if (containsKey(mapModel, neighbour[0], neighbour[1]))
            {
                Coordinates coordinates = new Coordinates(neighbour[0], neighbour[1]);
                neighbours.add(coordinates);
            }
        }
        return neighbours;
    }
    public static boolean containsKey(int[][] mapModel, int mapWidth, int mapLength)
    {
        try {
            mapModel[mapWidth - 1][mapLength - 1] = 0;
            return true;
        }catch (ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
    }
}
