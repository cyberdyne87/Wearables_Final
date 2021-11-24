package com.example.wearables_final;

import android.graphics.Bitmap;
import android.graphics.Canvas;

// this is the base class for all drawable objects in game
public class DrawnObject {
    public Bitmap sprite;
    public float xPos;
    public float yPos;
    public float angle;

    public DrawnObject(Bitmap image, float x, float y, float rotation) {
        sprite = image;
        xPos = x;
        yPos = y;
        angle = rotation;
    }

    public DrawnObject(Bitmap image) {
        sprite = image;
    }

    public void draw(Canvas c) {
        c.save();
            // (canvas/sprite center x/y)
            float CCX = c.getWidth() / 2;
            float CCY = c.getHeight() / 2;
            float SCX = sprite.getWidth() / 2;
            float SCY = sprite.getHeight() / 2;

            c.translate(xPos, yPos);
            c.rotate(angle, CCX, CCY);
            c.drawBitmap(sprite, CCX - SCX, CCY - SCY, null);
        c.restore();
    }
}
