package org.example;

import org.example.entities.*;

import java.util.Random;

public class InitActions {


    private static final int CAT_SPAWN_COUNT = 1;
    private static final int MOUSE_SPAWN_COUNT = 1;
    private static final int CHEESE_SPAWN_COUNT = 3;
    private static final int CAT_FOOD_SPAWN_COUNT = 3;
    private static final int BOX_SPAWN_COUNT = 3;
    private final Map map;
    private final EntityFactory entityFactory;

    public InitActions(Map map, EntityFactory entityFactory) {
        this.map = map;
        this.entityFactory = entityFactory;
    }

    public void setUpDefaultEntities() {
        for (int i = 0; i < CAT_SPAWN_COUNT; i++) {
            Cat cat = new Cat();
            entityFactory.putEntity(cat, map);
        }
        for (int i = 0; i < MOUSE_SPAWN_COUNT; i++) {
            Mouse mouse = new Mouse();
            entityFactory.putEntity(mouse, map);
        }
        for (int i = 0; i < CHEESE_SPAWN_COUNT; i++) {
            MouseFood mouseFood = new MouseFood();
            entityFactory.putEntity(mouseFood, map);
        }
        for (int i = 0; i < CAT_FOOD_SPAWN_COUNT; i++) {
            CatFood food = new CatFood();
            entityFactory.putEntity(food, map);
        }
        for (int i = 0; i < BOX_SPAWN_COUNT; i++) {
            Box box = new Box();
            entityFactory.putEntity(box, map);
        }
    }

    public void initMap() {
        for (int i = 1; i <= map.WIDTH; i++) {
            for (int j = 1; j <= map.LENGTH; j++) {
                map.simulationMap.put(new Coordinates(i, j), null);
            }
        }
    }
}
