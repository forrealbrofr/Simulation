package org.example;

import org.example.actions.Actions;
import org.example.actions.CycleActions;
import org.example.actions.IterateActions;
import org.example.actions.MapInitActions;
import org.example.entities.dynamicObjects.Cat;
import org.example.entities.dynamicObjects.Mouse;

import java.util.Scanner;

public class Simulation {
    private final Map simulationMap;
    private static final Scanner scanner = new Scanner(System.in);
    public static int currentIteration = 0;

    public Simulation(Map simulationMap) {
        this.simulationMap = simulationMap;
        resetMap();
    }
    private void resetMap()
    {
        MapInitActions initActions = new MapInitActions(simulationMap);
        initActions.perform();
        MapConsoleRenderer renderer = new MapConsoleRenderer();
        renderer.printMap(simulationMap);
    }

    public void startSimulation()
    {
        showMenuOptions();
        String menuInput = scanner.nextLine();
        switch (menuInput.trim())
        {
            case "1":
                Actions actions = new IterateActions(simulationMap);
                actions.perform();
                startSimulation();
            case "2":
                CycleActions cycleActions = new CycleActions(simulationMap);
                cycleActions.perform();
                startSimulation();
            case "3":
                resetMap();
                startSimulation();
                break;
            case "0":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuInput.trim());
        }
    }
    public static boolean isGameOver(Map map)
    {
        return !map.containsEntityClass(Mouse.class.getSimpleName()) || !map.containsEntityClass(Cat.class.getSimpleName());
    }

    private void showMenuOptions()
    {
        System.out.println("------Simulation Menu-----");
        System.out.println("1 - iterate one time");
        System.out.println("2 - start cycle");
        System.out.println("3 - reset");
        System.out.println("0 - quit");
    }
}
