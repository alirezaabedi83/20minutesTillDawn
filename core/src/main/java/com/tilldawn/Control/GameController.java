package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.Main;
import com.tilldawn.Model.EnemySpawner;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.SaveData.User;
import com.tilldawn.Model.SaveData.UserManager;
import com.tilldawn.Model.Weapon;
import com.tilldawn.View.GameOverView;
import com.tilldawn.View.GameView;
import com.tilldawn.View.PauseMenuView;

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

        Game.getInstance().setController(this);

    }

    public void updateGame(float delta) {
        if (Game.getInstance().isPaused()) return;
        if (view != null) {

            // Update all systems
            worldController.update();
            playerController.update(delta);

            for (Ability ability : playerController.getPlayer().getAbilities().values()) {
                ability.update(delta, playerController.getPlayer());
            }

            weaponController.update(delta);
            enemySpawner.update(delta, playerController.getPlayer());

            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                Game.getInstance().setPaused(true);
                Main.getMain().setScreen(new PauseMenuView(playerController.getPlayer(),this));
                return;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
                Game.getInstance().setElapsedTime(Game.getInstance().getElapsedTime()+60);
                enemySpawner.setGameTime(enemySpawner.getGameTime()+60);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
                playerController.getPlayer().increaseLevel();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                if (playerController.getPlayer().getPlayerHealth()<3){
                    playerController.getPlayer().setPlayerHealth(playerController.getPlayer().getPlayerHealth()+2);
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
                Game.getInstance().setElapsedTime(Game.getInstance().getTotalGameTime()/2);
                enemySpawner.setGameTime(Game.getInstance().getTotalGameTime()/2);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
                playerController.getPlayer().setPlayerHealth(10000);
            }



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

    public void gameOver(boolean won) {
        Player player = Game.getInstance().getCurrentPlayer();
        int timeAlive = (int) Game.getInstance().getElapsedTime();
        int kills = player.getKills();
        int score = timeAlive * kills;

        User user= Main.getCurrentUser();
        if (user!= null) {
            user.setTimeAlive(timeAlive);
            user.setKillCount(kills);
            user.setScore(score);

            UserManager userManager=new UserManager();
            userManager.setCurrentUser(user);
            userManager.saveCurrentUser();
        }

        Main.getMain().setScreen(new GameOverView(won, player,timeAlive));
    }



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