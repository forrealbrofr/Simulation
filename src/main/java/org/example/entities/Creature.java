package org.example.entities;

import org.example.*;
import org.example.entities.items.Buffer;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature extends Entity {
    private final static int FAMINE_DAMAGE = 20; // each iteration damage if the creature is hungry
    private final int viability; // num of iterations they won't suffer from hunger
    protected int healthPoints;
    protected boolean isTriggered;
    protected int iterationsWithoutFoodCount = 0;
    private int walkSpeed;
    private int runSpeed;
    protected List<Buffer> currentBuffs = new ArrayList<>();

    protected Coordinates currentCoordinates;
    public Creature(Coordinates currentCoordinates, int viability, int starterHealthPoints,
                    int walkSpeed, int runSpeed) {
        this.currentCoordinates = currentCoordinates;
        this.viability = viability;
        this.healthPoints = starterHealthPoints;
        this.walkSpeed = walkSpeed;
        this.runSpeed = runSpeed;
    }
    public abstract void move(Map map);
    protected abstract Coordinates choosePlaceWhereToRun(Map map, Coordinates nearestTrigger);
    protected abstract Coordinates choosePlaceWhereToWalk(Map map);
    protected abstract void handleReceivedItem(Entity entity);
    protected void makeMove(Map map, Coordinates to){
        this.checkBuffsState();
        PathFinder pathFinder = new PathFinder(map, currentCoordinates, to);
        Coordinates nextCoordinate = chooseNextCoordinate(pathFinder.getPath());
        checkPossibleItemOnNextCoordinate(map.getEntity(nextCoordinate));
        shiftCoordinates(map, nextCoordinate);
    }
    private void shiftCoordinates(Map map, Coordinates nextCoordinate) {
        map.setEntity(currentCoordinates, null);
        currentCoordinates = nextCoordinate;
        map.setEntity(currentCoordinates, this);
    }

    private void checkPossibleItemOnNextCoordinate(Entity entity) {
        if (entity != null) // means creature got something, either a buffer or food
        {
            handleReceivedItem(entity);
        }
    }
    private Coordinates chooseNextCoordinate(List<Coordinates> fullPath) {
        final int speed = isTriggered ? runSpeed : walkSpeed;
        if (speed >= fullPath.size())
            return fullPath.get(fullPath.size() - 1);
        else
            return fullPath.get(speed);
    }

    protected void handleHungerState() {
        iterationsWithoutFoodCount++;
        boolean isHungry = iterationsWithoutFoodCount >= viability;
        if (isHungry)
        {
            healthPoints -= FAMINE_DAMAGE;
        }
    }
    protected boolean isCreatureDead(Map map) {
        if (healthPoints <= 0)
        {
            System.out.println(getIcon() + "\t" + currentCoordinates + "\t" + getIcon() +  " is dead:(");
            map.setEntity(currentCoordinates, null);
            return true;
        }
        return false;
    }
    public void increaseHealthPoints(int amount)
    {
        healthPoints += amount;
    }
    public void buffSpeed(int multiplier)
    {
        walkSpeed *= multiplier;
        runSpeed *= multiplier;
    }
    public void nerfSpeed(int multiplier)
    {
        walkSpeed /= multiplier;
        runSpeed /= multiplier;
    }
    private void checkBuffsState()
    {
        currentBuffs.removeIf(buff -> buff.hasBuffExpired(this));
    }

}
