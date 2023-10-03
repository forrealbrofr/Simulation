package org.example.actions;

import org.example.Map;
import org.example.entities.dynamicObjects.Mouse;
import org.example.factories.*;

import java.util.List;

public class RespawnActions extends Actions{
    private final static List<String> allEntities;
    static
    {
        allEntities = List.of("CatFood", "MouseFood", "SpeedBuffer", "InvisibilityBuffer", "Cat", "Mouse");
    }
    public RespawnActions(Map map) {
        super(map);
    }
    @Override
    public void perform() {
        checkForEntityAbsence();
    }

    public void checkForEntityAbsence()
    {
        for (String entity : allEntities) {
            if (!map.containsEntityClass(entity))
            {
                respawnConcreteItem(entity);
            }
        }
    }

    private void respawnConcreteItem(String entityClassName) {
        switch (entityClassName) {
            case "Mouse":
                map.setEntityOnToRandomPlace(new MouseFactory());
                System.out.println("🐭 reborns!");
                break;
            case "Cat":
                map.setEntityOnToRandomPlace(new CatFactory());
                System.out.println("😼 reborns!");
                break;
            case "CatFood":
                map.setEntityOnToRandomPlace(new CatFoodFactory());
                System.out.println("🥩 respawns");
                break;
            case "MouseFood":
                map.setEntityOnToRandomPlace(new MouseFoodFactory());
                System.out.println("🧀 respawns");
                break;
            case "InvisibilityBuffer":
                map.setEntityOnToRandomPlace(new InvisibilityBufferFactory());
                System.out.println("✨ respawns");
                break;
            case "SpeedBuffer":
                map.setEntityOnToRandomPlace(new SpeedBufferFactory());
                System.out.println("🌀 respawns");
                break;
        }
    }
}
