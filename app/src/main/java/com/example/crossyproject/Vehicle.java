package com.example.crossyproject;

import android.graphics.Canvas;

public class Vehicle extends GameObject {
    private int velocity;
    private int length;
    private boolean offScreen;

    public Vehicle(int row, int col, int velocity, Sprite sprite) {
        super(row, col);
        x = (col == 0) ? (-4 * TileMap.TILE_DIMENSION) : (x + TileMap.TILE_DIMENSION);
        this.velocity = velocity;
        this.sprite = sprite;
        offScreen = false;
        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    public void update() {
        x += velocity;
        if (x < -6 * TileMap.TILE_DIMENSION
                || x > (TileMap.getNumColumns() + 3) * TileMap.TILE_DIMENSION) {
            offScreen = true;
        }
    }
    @Override
    public void draw(Canvas canvas) {
        if (velocity > 0) {
            sprite.draw(canvas, x, y, true);
        } else {
            sprite.draw(canvas, x, y, false);
        }
    }

    public boolean isOffscreen() {
        return offScreen;
    }
}
