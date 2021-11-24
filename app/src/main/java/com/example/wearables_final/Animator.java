package com.example.wearables_final;

public class Animator extends Thread {
    GameActivity surfaceActivity;
    boolean isRunning = false;

    public Animator(GameActivity activity) {
        surfaceActivity = activity;
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            surfaceActivity.draw();
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void finish() {
        isRunning = false;
    }
}
