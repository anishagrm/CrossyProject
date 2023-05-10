package com.example.crossyproject;

import android.content.Context;

import java.util.Random;

public class Road {
    private int minimumVelocity = 5;
    private int maximumVelocity = 15;
    private boolean visited = false;
    private final int row;
    private int velocity;
    private int length;
    private Sprite vehicleSprite;
    private SpriteSheet spriteSheet;
    private int vehicleSpriteId;

    private int direction;
    private int scoreWhenCrossed;
    private double minUpdatesPerSpawn;
    private double minUpdatesUntilNextSpawn;

    // Checks if a vehicle is ready to spawn based on time
    public boolean readyToSpawn() {
        Random rand = new Random();
        if (minUpdatesUntilNextSpawn <= 0) {
            // If it's been too long, skip the 1/90 chance to spawn
            if (minUpdatesUntilNextSpawn > minUpdatesPerSpawn * -1) {
                if (rand.nextInt(90) != 0) {
                    return false;
                }
            }
            minUpdatesUntilNextSpawn = minUpdatesPerSpawn;
            return true;
        } else {
            minUpdatesUntilNextSpawn--;
            return false;
        }
    }
    public Road(int row, Context context) {
        spriteSheet = new SpriteSheet(context);
        Random rand = new Random();
        // Randomly generate length, 10% 3, 30% 2, 60% 1
        switch (rand.nextInt(8) + 1) {
        case 8:
            length = 3;
            break;
        case 7:
        case 6:
            length = 2;
            break;
        default:
            length = 1;
        }
        // Randomly generate direction (50/50)
        direction = (rand.nextInt(2) % 2 == 0) ? 1 : -1;
        velocity = (minimumVelocity
            + rand.nextInt((maximumVelocity - minimumVelocity) + 1)) * direction;
        this.row = row;
        // Can consider changing and overhauling vehicle sprites to include more vehicles
        vehicleSpriteId = length - 1;
        vehicleSprite = spriteSheet.getVehicleSprite(vehicleSpriteId, velocity);
        minUpdatesPerSpawn = ((double) vehicleSprite.getWidth() / Math.abs(velocity)) * 1.5;
        minUpdatesUntilNextSpawn = minUpdatesPerSpawn;
        scoreWhenCrossed = calculateScoreIncrease();
    }
    public Vehicle spawnVehicle() {
        int col = (velocity > 0) ? 0 : TileMap.getNumColumns();
        return new Vehicle(row, col, velocity, vehicleSprite);
    }
    // Left as a separate method for potentially more complicated score calculations (e.g. velocity)
    private int calculateScoreIncrease() {
        return 10 + length * 10;
    }

    public int cross() {
        if (!visited) {
            visited = true;
            return scoreWhenCrossed;
        } else {
            return 0;
        }
    }
}
