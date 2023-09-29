package org.example;

import org.example.entities.Entity;

import java.util.Random;

public class EntityFactory {
    private static final Random random = new Random();
    public void putEntity(Entity entity, Map map) {
        boolean isCellFilled = false;
        while (!isCellFilled) {
            int x = random.nextInt(1, map.WIDTH);
            int y = random.nextInt(1, map.LENGTH);
            Coordinates coordinate = new Coordinates(x, y);
            if (map.simulationMap.get(coordinate) == null) {
                isCellFilled = true;
                map.setEntity(coordinate, entity);
            }
        }
    }
}
