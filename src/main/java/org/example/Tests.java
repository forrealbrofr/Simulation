package org.example;

import org.example.entities.*;

public class Tests {
    public static void main(String[] args) {
        Map map = new Map();
        map.setUp();
        new MapConsoleRenderer().printMap(map);
        TargetFinder targetFinder = new TargetFinder(map, new Coordinates(1, 1));
        System.out.println(targetFinder.getTargetCoordinate(new MouseFood()));
    }
}