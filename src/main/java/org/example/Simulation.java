package org.example;

import java.util.Scanner;

public class Simulation {
    Scanner scanner = new Scanner(System.in);
    public void startSimulation()
    {
        int countOfIterations = 0;
        showMenuOptions();
        Map simulationMap = new Map();
        String menuInput = scanner.nextLine();
        switch (menuInput.trim())
        {
            case "1":

                break;
            case "3":
                startSimulation();
                break;
            case "0":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuInput.trim());
        }
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
