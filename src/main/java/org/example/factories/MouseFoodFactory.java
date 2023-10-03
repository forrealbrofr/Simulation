package org.example.factories;

import org.example.entities.Entity;
import org.example.entities.items.MouseFood;

public class MouseFoodFactory implements EntityFactory{
    @Override
    public Entity createEntity(int x, int y) {
        return new MouseFood();
    }
}
