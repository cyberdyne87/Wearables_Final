package com.example.wearables_final;

import android.graphics.Bitmap;

public class FallingAsteroid extends FallingObject {

    public FallingAsteroid(Bitmap image, int catchSound, float rotationSpeed, float fallSpeed, float x) {
        super(image, catchSound, rotationSpeed, fallSpeed, 0, x);
    }

    @Override
    public void consequences(GameActivity game) {
        if (game.lives > 0)
            game.lives--;
    }
}