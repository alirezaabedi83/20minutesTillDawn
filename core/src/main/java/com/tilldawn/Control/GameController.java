package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.Main;
import com.tilldawn.Model.EnemySpawner;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.Weapon;
import com.tilldawn.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private EnemySpawner enemySpawner;
    private float gameTime = 0;
    private float gameDuration = 20 * 60; // 20 minutes in seconds

    public void setView(GameView view ,OrthographicCamera camera) {
        this.view = view;

        Player player = new Player();
        playerController = new PlayerController(player,camera);
        worldController = new WorldController(camera);
        weaponController = new WeaponController(new Weapon("SMG"), player);
        enemySpawner = new EnemySpawner();

        // Set up camera

    }

    public void updateGame(float delta) {
        if (view != null) {
            gameTime += delta;

            // Update all systems
            worldController.update();
            playerController.update(delta);
            weaponController.update(delta);
            enemySpawner.update(delta, playerController.getPlayer());

            // Check for game over conditions
            if (playerController.getPlayer().getPlayerHealth() <= 0) {
                // Player died
                gameOver(false);
            } else if (gameTime >= gameDuration) {
                // Time's up - player survived
                gameOver(true);
            }
        }
    }

    private void gameOver(boolean won) {
        // TODO: Show game over screen with stats
        if (won) {
            System.out.println("You survived! Congratulations!");
        } else {
            System.out.println("Game Over - You died!");
        }
    }

    // ... getters for controllers ...


    public GameView getView() {
        return view;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public void setWorldController(WorldController worldController) {
        this.worldController = worldController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public void setWeaponController(WeaponController weaponController) {
        this.weaponController = weaponController;
    }

    public EnemySpawner getEnemySpawner() {
        return enemySpawner;
    }

    public void setEnemySpawner(EnemySpawner enemySpawner) {
        this.enemySpawner = enemySpawner;
    }

    public float getGameTime() {
        return gameTime;
    }

    public void setGameTime(float gameTime) {
        this.gameTime = gameTime;
    }

    public float getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(float gameDuration) {
        this.gameDuration = gameDuration;
    }


}