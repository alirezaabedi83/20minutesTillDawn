package com.tilldawn.Model;

import com.tilldawn.Model.Player;

public class Game {
    private static Game instance;

    private Player currentPlayer;
    private float totalGameTime;
    private float elapsedTime;
    private int wave;
    private boolean isGameOver;
    private boolean isPaused;

    private Game() {
        reset();
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
        isPaused=false;
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

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
