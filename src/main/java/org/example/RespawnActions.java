package org.example;

import org.example.entities.Entity;
import org.example.entities.Mouse;

import java.util.Random;

public class RespawnActions {
    private final Map map;
    private static final Random random = new Random();

    public RespawnActions(Map map) {
        this.map = map;
    }

    public void respawnEntity(Entity entity)
    {
       if (!doesMapContainGivenEntity(entity))
       {
           EntityFactory entityFactory = new EntityFactory();
           if (entity instanceof Mouse)
           {
               Mouse mouse = new Mouse();
               entityFactory.putEntity(mouse, map);
           }
           else {
               entityFactory.putEntity(entity, map);
           }
       }
    }
    private boolean doesMapContainGivenEntity(Entity entity)
    {
        for (Entity value : map.simulationMap.values()) {
            if (value != null && value.getClass().isInstance(entity))
            {
                return true;
            }
        }
        return false;
    }
}
