package com.tilldawn.Model;

import com.tilldawn.Model.Player;

public class Game {
    private static Game instance;

    private Player currentPlayer;
    private float totalGameTime;   // مثلاً 20 * 60
    private float elapsedTime;     // زمان سپری شده
    private int wave;              // موج فعلی دشمنان
    private boolean isGameOver;

    private Game() {
        reset(); // مقداردهی اولیه
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void reset() {
        totalGameTime = 20 * 60;
        elapsedTime = 0;
        wave = 0;
        isGameOver = false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public float getTotalGameTime() {
        return totalGameTime;
    }

    public void setTotalGameTime(float totalGameTime) {
        this.totalGameTime = totalGameTime;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void incrementElapsedTime(float delta) {
        this.elapsedTime += delta;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public void nextWave() {
        wave++;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
