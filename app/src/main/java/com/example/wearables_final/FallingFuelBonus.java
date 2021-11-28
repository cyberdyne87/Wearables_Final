package com.example.wearables_final;

import android.graphics.Bitmap;

public class FallingFuelBonus extends FallingObject {

    float fuelBonus;

    public FallingFuelBonus(Bitmap image, int catchSound, float rotationSpeed, float fallSpeed, float x, float fuelBonus) {
        super(image, catchSound, rotationSpeed, fallSpeed, x);
        this.fuelBonus = fuelBonus;
    }

    @Override
    public void consequences(GameActivity game) {
        game.fuelRemaining += fuelBonus;
    }

}
