package com.example.crossyproject;

import android.graphics.Canvas;

public abstract class Tile extends GameObject {
    protected Sprite sprite;
    public Tile(int row, int col) {
        super(row, col);
    }

    public void draw(Canvas canvas) {
        if (sprite != null) {
            sprite.draw(canvas, x, y, false);
        }
    }
}
