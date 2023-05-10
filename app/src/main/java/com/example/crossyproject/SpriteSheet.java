package com.example.crossyproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;

public class SpriteSheet {
    // Bitmap - a 2d array/grid of individual pixels, each with an RGB value
    private Bitmap bitmap;
    private Bitmap flippedMap;
    private static int gridDimension = 128;

    // Constructor, boilerplate for converting the sprite_sheet.png to a bitmap
    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.sprite_sheet, bitmapOptions);
        flippedMap = flip(bitmap);
    }

    private Bitmap flip(Bitmap bitmap) {
        Matrix m = new Matrix();
        m.postScale(-1, 1, bitmap.getWidth() / 2f, bitmap.getHeight() / 2f);
        Bitmap test = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), m, true);
        return test;
    }

    // Gets the player sprite (of the first three sprites on the sheet on the first row)
    public Sprite getPlayerSprite(int spriteId) {
        if (spriteId > 2 || spriteId < 0) {
            return null;
        }
        int left = spriteId * gridDimension;
        int right = (spriteId + 1) * gridDimension;
        return new Sprite(this, new Rect(left, 0, right, gridDimension),
                gridDimension, gridDimension);
    }
    // Gets the tile sprite (third row, 0:Road, 1:Safe, 2:River, 3:Goal)
    public Sprite getTileSprite(int tileId) {
        if (tileId > 3 || tileId < 0) {
            return null;
        }
        int left = tileId * gridDimension;
        int right = (tileId + 1) * gridDimension;
        return new Sprite(this,
                new Rect(left, gridDimension, right, gridDimension * 2),
                gridDimension, gridDimension);
    }

    public Sprite getVehicleSprite(int vehicleSpriteId, int velocity) {
        Rect rect;
        int length;
        boolean flipped = (velocity > 0);
        int top;
        int bottom;
        switch (vehicleSpriteId) {
        case 0:
            top = gridDimension * 2;
            bottom = gridDimension * 3;
            length = 1;
            break;
        case 1:
            length = 2;
            top = gridDimension * 3;
            bottom = gridDimension * 4;
            break;
        case 2:
            length = 3;
            top = gridDimension * 4;
            bottom = gridDimension * 5;
            break;
        default:
            throw new IllegalArgumentException("Invalid vehicle sprite ID");
        }
        if (!flipped) {
            rect = new Rect(0, top, gridDimension * length, bottom);
        } else {
            rect = new Rect(flippedMap.getWidth() - (gridDimension * length),
                    top, flippedMap.getWidth(), bottom);
        }
        return new Sprite(this, rect, gridDimension * length, gridDimension);
    }
    // Getter for the sprite sheet bitmap
    public Bitmap getBitmap(boolean flipped) {
        return (flipped) ? flippedMap : bitmap;
    }
}
