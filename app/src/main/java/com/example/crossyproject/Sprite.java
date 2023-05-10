package com.example.crossyproject;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    private final SpriteSheet spriteSheet;
    private final Rect spriteSheetRect;
    private int width;
    private int height;
    // Constructor that takes in a sprite sheet and a rectangle with coordinates corresponding
    // to that sprite location on the sprite sheet
    public Sprite(SpriteSheet spriteSheet, Rect rect, int width, int height) {
        this.spriteSheet = spriteSheet;
        this.spriteSheetRect = rect;
        this.width = width;
        this.height = height;
    }
    // Draws the sprite onto the canvas
    public void draw(Canvas canvas, int x, int y, boolean flipped) {
        // Can control how big the sprite is scaled with the right and bottom params in Rect()
        canvas.drawBitmap(spriteSheet.getBitmap(flipped), spriteSheetRect,
                new Rect(x, y, x + width, y + height), null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
