package com.example.wearables_final;

import android.graphics.Bitmap;

public class Rocketship extends DrawnObject {

    public float speed; // how quickly the rocket moves
    public float width; // how wide the rocket is (for collisions)
    public float height; // how tall the rocket is (for collisions)

    public Rocketship(Bitmap image, float width, float height, float x, float y, float speed) {
        super(image, x, y, 0);

        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public void update(float accelX, float accelY, float minHori, float maxHori, float minVerti, float maxVerti, float buffer) {
        xPos += accelX * speed;
        yPos += accelY * speed;

        if (xPos > maxHori - width / 2 - buffer) xPos = maxHori - width / 2 - buffer;
        else if (xPos < minHori + width / 2 + buffer) xPos = minHori + width / 2 + buffer;

        if (yPos > maxVerti - height / 2 - buffer) yPos = maxVerti - height / 2 - buffer;
        else if (yPos < minVerti + height / 2 + buffer) yPos = minVerti + height / 2 + buffer;
    }
}
