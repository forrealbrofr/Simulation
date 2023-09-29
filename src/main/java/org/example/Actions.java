package org.example;

import org.example.entities.*;

import java.util.ArrayList;

public class Actions {
    private final ArrayList<Creature> creatures = new ArrayList<>();
    private final Map map;

    public Actions(Map map) {
        this.map = map;
    }

    public void iterate()
    {
        makeConcreteCreaturesMove(new Mouse());
        makeConcreteCreaturesMove(new Cat());
    }

    private void makeConcreteCreaturesMove(Creature concreteCreature){
        fillInCreaturesList();
        for (Creature creature : creatures) {
            if (creature.getClass().isInstance(concreteCreature))
            {
                creature.move(map);
            }
        }
    }
    public void handleEvent(Entity entity)
    {
        if (entity instanceof Creature)
        {
            creatures.remove(entity);
            new RespawnActions(map).respawnEntity(entity);
        }
        else if (entity instanceof Items)
        {
            new RespawnActions(map).respawnEntity((Items) entity);
        }
    }
    private void fillInCreaturesList()
    {
        for (Entity value : map.simulationMap.values()) {
            if (value instanceof Creature)
                creatures.add((Creature) value);
        }
    }

    public static void startCycle()
    {
        // while(!exit condition)
        //       iterate();
    }

    public static void main(String[] args) {
        Actions actions = new Actions(new Map());
        MapConsoleRenderer renderer = new MapConsoleRenderer();
        actions.map.setUp();


        renderer.printMap(actions.map);
        System.out.println("1st iteration");
        actions.iterate();
        renderer.printMap(actions.map);
        System.out.println("second");
        actions.iterate();
        System.out.println("--------------------------");
        renderer.printMap(actions.map);
    }
}
