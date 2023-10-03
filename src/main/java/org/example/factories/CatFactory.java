package org.example.factories;

import org.example.Coordinates;
import org.example.entities.dynamicObjects.Cat;
import org.example.entities.Entity;

public class CatFactory implements EntityFactory {
    public Entity createEntity(int x, int y) {
        Coordinates coordinate = new Coordinates(x, y);
        return new Cat(coordinate);
    }
}
