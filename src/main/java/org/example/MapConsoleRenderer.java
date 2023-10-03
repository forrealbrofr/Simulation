package org.example;

import org.example.entities.Entity;

public class MapConsoleRenderer {
    public void printMap(Map map) {
        System.out.println("Current iteration:  " + Simulation.currentIteration);
        for (int i = 1; i <= map.getWidth(); i++) {
            StringBuilder builder = new StringBuilder("\u001B[40m");
            for (int j = 1; j <= map.getLength(); j++) {
                Entity myEntity = map.getEntity(i, j);
                if (myEntity != null)
                {
                    builder.append(myEntity.getIcon()).append(" ");
                }
                else
                {
                    builder.append(" , ");
                }
            }
            System.out.println(builder.append("\u001B[0m"));
        }
    }
}
