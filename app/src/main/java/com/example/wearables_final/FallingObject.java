package com.example.wearables_final;

import android.graphics.Bitmap;

import java.util.Random;

// this is a basic falling candy
public class FallingObject extends DrawnObject {

    public float rotationSpeed = 0f;
    public float fallSpeed = 0f;
    public int scoreValue = 0;
    public int catchSound;

    public FallingObject(Bitmap image, int catchSound, float rotationSpeed, float fallSpeed, int scoreValue, float x) {
        super(image);

        this.catchSound = catchSound;
        this.rotationSpeed = rotationSpeed;
        this.fallSpeed = fallSpeed;
        this.scoreValue = scoreValue;
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
        game.score += scoreValue;
    }
}
