package com.example.crossyproject;

public class RoadTile extends Tile {
    public RoadTile(int row, int col, SpriteSheet spriteSheet) {
        super(row, col);
        sprite = spriteSheet.getTileSprite(0);
    }
}
