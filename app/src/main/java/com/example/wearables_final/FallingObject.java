package com.example.wearables_final;

import android.graphics.Bitmap;

import java.util.Random;

// this is a basic falling object
public class FallingObject extends DrawnObject {

    public float rotationSpeed = 0f;
    public float fallSpeed = 0f;
    public int collisionSound;

    public FallingObject(Bitmap image, int collisionSound, float rotationSpeed, float fallSpeed, float x) {
        super(image);

        this.collisionSound = collisionSound;
        this.rotationSpeed = rotationSpeed;
        this.fallSpeed = fallSpeed;
        xPos = x;
        yPos = -1000;
        Random rnd = new Random();
        if (rnd.nextBoolean()) this.rotationSpeed *= -1;
    }

    public void update() {
        angle += rotationSpeed;
        yPos += fallSpeed;
    }

    public void consequences(GameActivity game) {
    }
}
