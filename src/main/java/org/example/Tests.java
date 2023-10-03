package org.example;

import org.example.actions.CycleActions;
import org.example.actions.MapInitActions;
import org.example.entities.*;

public class Tests {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(new Map());
        simulation.startSimulation();
    }
}