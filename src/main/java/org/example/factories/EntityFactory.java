package org.example.factories;

import org.example.entities.Entity;

public interface EntityFactory {

    Entity createEntity(int x, int y);
}
