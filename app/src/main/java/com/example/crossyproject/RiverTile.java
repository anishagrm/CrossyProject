package com.example.crossyproject;

public class RiverTile extends Tile {
    public RiverTile(int row, int col, SpriteSheet spriteSheet) {
        super(row, col);
        sprite = spriteSheet.getTileSprite(2);
    }
}
