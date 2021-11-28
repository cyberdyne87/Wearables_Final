package com.example.wearables_final;

import android.graphics.Bitmap;

public class FallingHealthBonus extends FallingObject {

    public FallingHealthBonus(Bitmap image, int catchSound, float rotationSpeed, float fallSpeed, float x) {
        super(image, catchSound, rotationSpeed, fallSpeed, x);
    }

    @Override
    public void consequences(GameActivity game) {
        if (game.lives < 3)
            game.lives++;
    }
}
