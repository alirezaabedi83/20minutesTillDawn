package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.Main;
import com.tilldawn.Model.EnemySpawner;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.Weapon;
import com.tilldawn.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private EnemySpawner enemySpawner;

    public void setView(GameView view ,OrthographicCamera camera) {
        this.view = view;

        Player player = Game.getInstance().getCurrentPlayer();
        if (player == null) player = new Player("Hero 1");
        playerController = new PlayerController(player,camera);
        worldController = new WorldController(camera);
        weaponController = new WeaponController(player.getWeapon(), player);
        enemySpawner = new EnemySpawner();
        weaponController.setEnemySpawner(enemySpawner);

    }

    public void updateGame(float delta) {
        if (view != null) {

            // Update all systems
            worldController.update();
            playerController.update(delta);
            weaponController.update(delta);
            enemySpawner.update(delta, playerController.getPlayer());


            Game.getInstance().incrementElapsedTime(delta);

            if (Game.getInstance().getElapsedTime() >= Game.getInstance().getTotalGameTime()) {
                gameOver(true);
            }

            // Check for game over conditions
            if (playerController.getPlayer().getPlayerHealth() <= 0) {
                // Player died
                gameOver(false);
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

}