package com.example.crossyproject;

import android.graphics.Canvas;

public class Player extends GameObject {

    private final GameSurface gS;

    public Player(int row, int col, Sprite sprite, GameSurface gameSurface) {
        super(row, col);
        this.sprite = sprite;
        this.gS = gameSurface;
        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, x, y, false);
    }

    public void update() {

    }
    @Override
    public void setCol(int col) {
        this.col = Math.max(Math.min(TileMap.getNumColumns() - 1, col), 0);
        super.updateCoordinates();
        setPositionPixels(x, y);
    }
    @Override
    public void setRow(int row) {
        this.row = Math.max(Math.min(TileMap.getNumRows() - 1, row), 0);
        super.updateCoordinates();
        setPositionPixels(x, y);
    }
    @Override
    public void setPosition(int row, int col) {
        setRow(row);
        setCol(col);
        super.updateCoordinates();
    }
    public void setPositionPixels(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }
    public void moveLeft() {
        this.setCol(this.col - 1);
    }
    public void moveRight() {
        this.setCol(this.col + 1);
    }
    public void moveUp() {
        this.setRow(this.row - 1);
        if (row > TileMap.getSafeRow()) {
            gS.incrementScore(gS.getRoads()[row - (TileMap.getSafeRow() + 1)].cross());
        }
    }
    public void moveDown() {
        this.setRow(this.row + 1);
    }
}
