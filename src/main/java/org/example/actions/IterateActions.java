package org.example.actions;

import org.example.Map;
import org.example.MapConsoleRenderer;
import org.example.Simulation;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.entities.dynamicObjects.Cat;
import org.example.entities.dynamicObjects.Mouse;

import java.util.ArrayList;

public class IterateActions extends Actions{
    public IterateActions(Map map) {
        super(map);
    }
    private final RespawnActions respawnActions = new RespawnActions(map);
    private final MapConsoleRenderer renderer = new MapConsoleRenderer();
    private final static String MOUSES_CLASS_NAME = Mouse.class.getSimpleName();
    private final static String CATS_CLASS_NAME = Cat.class.getSimpleName();
    @Override
    public void perform(){ // one iteration
        ArrayList<Creature> allCreatures = new ArrayList<>();
        assignAllCreatures(allCreatures);
        moveConcreteCreatures(MOUSES_CLASS_NAME, allCreatures);
        respawnActions.perform();
        moveConcreteCreatures(CATS_CLASS_NAME, allCreatures);
        respawnActions.perform();
        Simulation.currentIteration++;
        renderer.printMap(map);
    }

    private void moveConcreteCreatures(String creatureClassName, ArrayList<Creature> creatures) {
        for (Creature creature : creatures) {
            if (creature.getClass().getSimpleName().equalsIgnoreCase(creatureClassName))
            {
                creature.move(map);
            }
        }
    }

    private void assignAllCreatures(ArrayList<Creature> list)
    {
        for (int i = 1; i <= map.getWidth(); i++) {
            for (int j = 1; j <= map.getLength(); j++) {
                Entity entity = map.getEntity(i, j);
                if (entity instanceof Creature)
                {
                    list.add((Creature) entity);
                }
            }
        }
    }
}
