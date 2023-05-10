package com.example.crossyproject;

public class SafeTile extends Tile {
    public SafeTile(int row, int col, SpriteSheet spriteSheet) {
        super(row, col);
        sprite = spriteSheet.getTileSprite(1);
    }
}
