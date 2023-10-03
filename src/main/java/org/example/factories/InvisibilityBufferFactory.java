package org.example.factories;

import org.example.entities.items.InvisibilityBuffer;
import org.example.entities.Entity;

public class InvisibilityBufferFactory implements EntityFactory{
    @Override
    public Entity createEntity(int x, int y) {
        return new InvisibilityBuffer();
    }
}
