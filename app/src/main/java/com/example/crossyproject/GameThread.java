package com.example.crossyproject;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    public static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1000.0 / MAX_UPS;
    private boolean running = false;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;

    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        super.run();
        // Game Loop logic
        int updateCount = 0;
        int frameCount = 0;
        // Times in milliseconds
        long startTime;
        long elapsedTime;
        long sleepTime;
        startTime = System.currentTimeMillis();
        while (running) {
            Canvas canvas = null;

            try {
                // Gets canvas and locks it in place
                canvas = surfaceHolder.lockCanvas();
                synchronized (canvas) {
                    // Update the game
                    this.gameSurface.update();
                    updateCount++;
                    // Render the game
                    this.gameSurface.draw(canvas);
                }
            } catch (IllegalArgumentException iae) {
                // Occurs if the canvas is already locked
                iae.printStackTrace();
            } finally {
                // Unlock the canvas and actually display it (post)
                if (canvas != null) {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                    frameCount++;
                }
            }
            elapsedTime = System.currentTimeMillis() - startTime;
            // Make sure updates per second doesn't exceed a set cap
            sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // Make sure updates per second isn't too low (skips frames if necessary)
            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                gameSurface.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            }

            // If one second has passed, update the UPS and FPS counts and reset them
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (elapsedTime / 1000.0);
                averageFPS = frameCount / (elapsedTime / 1000.0);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    // Starts the main game loop using Thread class's run method
    public void startLoop() {
        running = true;
        start();
    }
    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }
}
