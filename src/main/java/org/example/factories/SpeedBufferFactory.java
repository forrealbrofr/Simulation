package org.example.factories;

import org.example.entities.Entity;
import org.example.entities.items.SpeedBuffer;

public class SpeedBufferFactory implements EntityFactory {
    @Override
    public Entity createEntity(int x, int y) {
        return new SpeedBuffer();
    }
}
