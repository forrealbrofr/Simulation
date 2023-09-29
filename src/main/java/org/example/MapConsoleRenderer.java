package org.example;

import org.example.entities.Entity;

public class MapConsoleRenderer {

    public void printMap(Map map) {
        for (int i = 1; i <= map.WIDTH; i++) {
            StringBuilder builder = new StringBuilder("\u001B[40m");
            for (int j = 1; j <= map.LENGTH; j++) {
                Entity myEntity = map.simulationMap.get(new Coordinates(i, j));
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

    public static void main(String[] args) {
        Map map = new Map();
        map.setUp();
        new MapConsoleRenderer().printMap(map);
    }
}
