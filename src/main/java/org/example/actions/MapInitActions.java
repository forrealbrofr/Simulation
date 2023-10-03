package org.example.actions;

import org.example.*;
import org.example.factories.*;

public class MapInitActions extends Actions{
    /**
     * configure how many concrete entities will spawn
     */
    private static final int CAT_SPAWN_COUNT = 3;
    private static final int MOUSE_SPAWN_COUNT = 5;
    private static final int MOUSE_FOOD_SPAWN_COUNT = 25;
    private static final int CAT_FOOD_SPAWN_COUNT = 15;
    private static final int BOX_SPAWN_COUNT = 10;
    private static final int SPEED_SPAWN_COUNT = 10;

    public MapInitActions(Map map) {
        super(map);
    }

    @Override
    public void perform() {
        initMap();
        setUpDefaultEntities();
    }

    public void setUpDefaultEntities() {
        for (int i = 0; i < CAT_SPAWN_COUNT; i++) {
            map.setEntityOnToRandomPlace(new CatFactory());
        }
        for (int i = 0; i < MOUSE_SPAWN_COUNT; i++) {
            map.setEntityOnToRandomPlace(new MouseFactory());
        }
        for (int i = 0; i < CAT_FOOD_SPAWN_COUNT; i++) {
            map.setEntityOnToRandomPlace(new CatFoodFactory());
        }
        for (int i = 0; i < MOUSE_FOOD_SPAWN_COUNT; i++) {
            map.setEntityOnToRandomPlace(new MouseFoodFactory());
        }
        for (int i = 0; i < BOX_SPAWN_COUNT; i++) {
            map.setEntityOnToRandomPlace(new InvisibilityBufferFactory());
        }
        for (int i = 0; i < SPEED_SPAWN_COUNT; i++) {
            map.setEntityOnToRandomPlace(new SpeedBufferFactory());
        }
    }

    public void initMap() {
        for (int i = 1; i <= map.getWidth(); i++) {
            for (int j = 1; j <= map.getLength(); j++) {
                map.setEntity(i, j, null);
            }
        }
    }
}
