package com.example.crossyproject;

import android.graphics.Canvas;

public abstract class GameObject {
    // Horizontal position (left edge) in pixels
    protected int x;

    // Vertical position (top edge) in pixels
    protected int y;

    protected Sprite sprite;
    protected int row;
    protected int col;
    protected int width;
    protected int height;

    public GameObject(int row, int col) {
        setPosition(row, col);
    }

    public abstract void draw(Canvas canvas);
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }
    public void setCol(int col) {
        this.col = col;
        this.updateCoordinates();
    }
    public void setRow(int row) {
        this.row = row;
        this.updateCoordinates();
    }
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        this.updateCoordinates();
    }
    public static boolean checkCollision(GameObject o1, GameObject o2) {
        if (o1.row == o2.row) {
            return (o1.x <= o2.x + o2.width && o1.x > o2.x - o1.width);
        }
        return false;
    }
    protected void updateCoordinates() {
        x = col * TileMap.TILE_DIMENSION;
        y = row * TileMap.TILE_DIMENSION;
    }
}
