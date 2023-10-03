package org.example;

import org.example.entities.Entity;
import org.example.factories.EntityFactory;

import java.util.HashMap;
import java.util.Random;

public class Map {
    private final int width = 25;
    private final int length = 25;
    private final HashMap<Coordinates, Entity> simulationMap = new HashMap<>();

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setEntity(Coordinates coordinate, Entity entity) {
        simulationMap.put(coordinate, entity);
    }
    public void setEntity(int x, int y, Entity entity) {
        Coordinates coordinate = new Coordinates(x, y);
        simulationMap.put(coordinate, entity);
    }
    public Entity getEntity(int x, int y)
    {
        return simulationMap.get(new Coordinates(x, y));
    }
    public Entity getEntity(Coordinates coordinate)
    {
        return simulationMap.get(coordinate);
    }
    public void setEntityOnToRandomPlace(EntityFactory entityFactory)
    {
        final Random random = new Random();
        boolean isChosenCellEmpty = false;
        while (!isChosenCellEmpty)
        {
            int x = random.nextInt(1, width + 1);
            int y = random.nextInt(1, length + 1);
            if (this.getEntity(x, y) == null)
            {
                isChosenCellEmpty = true;
                Entity entity = entityFactory.createEntity(x, y);
                this.setEntity(x, y, entity);
            }
        }
    }
    public boolean containsEntityClass(String entityClassName)
    {
        for (Entity value : simulationMap.values()) {
            if (value!= null && value.getClass().getSimpleName().equalsIgnoreCase(entityClassName))
            {
                return true;
            }
        }
        return false;
    }
}
