package com.example.crossyproject;

public class GoalTile extends Tile {
    public GoalTile(int row, int col, SpriteSheet spriteSheet) {
        super(row, col);
        sprite = spriteSheet.getTileSprite(3);
    }
}
