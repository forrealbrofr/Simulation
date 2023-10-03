package org.example.factories;

import org.example.entities.items.CatFood;
import org.example.entities.Entity;

public class CatFoodFactory implements EntityFactory {
    @Override
    public Entity createEntity(int x, int y) {
        return new CatFood();
    }
}
