package com.tilldawn.Model;

import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.Player;
import com.tilldawn.View.AbilitySelectionView;

public class Game {
    private static Game instance;

    private Player currentPlayer;
    private float totalGameTime;
    private float elapsedTime;
    private int wave;
    private boolean isGameOver;
    private boolean isPaused;
    private GameController controller;
    private boolean isBWMode = false;

    public void toggleBWMode() {
        isBWMode = !isBWMode;
    }

    public boolean isBWMode() {
        return isBWMode;
    }

    public void saveGame() {
        System.out.println("Game saved.");
    }


    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GameController getController() {
        return controller;
    }


    private Game() {
        reset();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
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

    public void showAbilitySelection() {
        Main.getMain().setScreen(new AbilitySelectionView(currentPlayer, controller));

    }
}
