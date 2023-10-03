package org.example.factories;

import org.example.Coordinates;
import org.example.entities.Entity;
import org.example.entities.dynamicObjects.Mouse;

public class MouseFactory implements EntityFactory {

    @Override
    public Entity createEntity(int x, int y) {
        Coordinates coordinate = new Coordinates(x, y);
        return new Mouse(coordinate);
    }
}
