package com.example.crossyproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class TileMap {
    public static final int TILE_DIMENSION = 128;
    private static int screenHeight = 1977;
    private static int screenWidth = 1080;
    private static int numRows = screenHeight / TILE_DIMENSION;
    private static int numColumns = screenWidth / TILE_DIMENSION;
    private static int safeRow = numRows * 2 / 5;

    private Tile[][] tileMap;
    private Tile[] offScreenTiles;
    private Bitmap mapBitmap;

    public TileMap(SpriteSheet spriteSheet, int sWidth, int sHeight) {
        screenWidth = sWidth;
        screenHeight = sHeight;
        initializeTileMap(spriteSheet);
    }

    private void initializeTileMap(SpriteSheet spriteSheet) {
        // Tile coordinates will start from top left, y down, x right
        tileMap = new Tile[numRows][numColumns];

        // Initialize goal tiles row
        for (int i = 0; i < numColumns; i++) {
            tileMap[0][i] = new GoalTile(0, i, spriteSheet);
        }
        // River tiles will go from goal row to 40% of the screen
        for (int i = 1; i < safeRow; i++) {
            for (int j = 0; j < numColumns; j++) {
                tileMap[i][j] = new RiverTile(i, j, spriteSheet);
            }
        }
        // Initialize safe tiles row
        for (int i = 0; i < numColumns; i++) {
            tileMap[safeRow][i] = new SafeTile(safeRow, i, spriteSheet);
        }
        // Initialize road tiles rows
        for (int i = safeRow + 1; i < numRows - 1; i++) {
            for (int j = 0; j < numColumns; j++) {
                tileMap[i][j] = new RoadTile(i, j, spriteSheet);
            }
        }
        // Initialize beginning safe tiles row
        for (int i = 0; i < numColumns; i++) {
            tileMap[numRows - 1][i] = new SafeTile(numRows - 1, i, spriteSheet);
        }

        // Initialize offScreen tiles, in a different data structure to separate functionality
        offScreenTiles = new Tile[numRows];
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                offScreenTiles[i] = new GoalTile(i, numColumns, spriteSheet);
            } else if (tileMap[i][0] instanceof SafeTile) {
                offScreenTiles[i] = new SafeTile(i, numColumns, spriteSheet);
            } else if (tileMap[i][0] instanceof RiverTile) {
                offScreenTiles[i] = new RiverTile(i, numColumns, spriteSheet);
            } else {
                offScreenTiles[i] = new RoadTile(i, numColumns, spriteSheet);
            }
        }

        // Draw the TileMap
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(screenWidth, screenHeight, config);
        Canvas mapCanvas = new Canvas(mapBitmap);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                tileMap[i][j].draw(mapCanvas);
            }
            offScreenTiles[i].draw(mapCanvas);
        }
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    public void draw(Canvas canvas) {
        Rect rect = new Rect(0, 0, screenWidth, screenHeight);
        canvas.drawBitmap(mapBitmap, rect, rect, null);
    }

    public static int getNumRows() {
        return numRows;
    }

    public static int getNumColumns() {
        return numColumns;
    }

    public static int getSafeRow() {
        return safeRow;
    }
}
