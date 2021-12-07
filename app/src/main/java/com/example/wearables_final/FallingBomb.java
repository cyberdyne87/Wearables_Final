package com.example.wearables_final;

import android.graphics.Bitmap;

public class FallingBomb extends FallingObject {

    public FallingBomb(Bitmap image, int catchSound, float rotationSpeed, float fallSpeed, float x) {
        super(image, catchSound, rotationSpeed, fallSpeed, x);
    }

    @Override
    public void consequences(GameActivity game) {
        game.triggeredBomb = true;
    }
}
