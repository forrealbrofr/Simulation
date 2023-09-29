package org.example;

import org.example.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    public final int WIDTH = 10;
    public final int LENGTH = 10;
    public HashMap<Coordinates, Entity> simulationMap = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        simulationMap.put(coordinates, entity);
    }
    public void setUp()
    {
        InitActions init = new InitActions(this, new EntityFactory());
        init.initMap();
        init.setUpDefaultEntities();
    }
    public List<Coordinates> getNeighbours(Coordinates coordinate)
    {
        int x = coordinate.x();
        int y = coordinate.y();
        int[][] allPossibleCoordinates = {{x + 1, y}, {x + 1, y + 1}, {x + 1,  y - 1}, {x - 1, y}, {x - 1, y - 1},
                {x - 1, y + 1}, {x, y + 1}, {x, y - 1}};
        List<Coordinates> neighbours = new ArrayList<>();

        for (int[] possibleCoordinate : allPossibleCoordinates) {
            Coordinates neighbourCoordinate = new Coordinates(possibleCoordinate[0], possibleCoordinate[1]);
            if (simulationMap.containsKey(neighbourCoordinate))
            {
                neighbours.add(neighbourCoordinate);
            }
        }
        return neighbours;
    }
    public int countStepsBetweenCoordinates(Coordinates coordinateOne, Coordinates coordinateTwo) {
        int stepsCount = 0;
        int xDifference = Math.abs(coordinateOne.x() - coordinateTwo.x());
        int yDifference = Math.abs(coordinateOne.y() - coordinateTwo.y());
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
}
