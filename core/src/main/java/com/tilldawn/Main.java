package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Control.LoginController;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SaveData.User;
import com.tilldawn.Model.SaveData.UserManager;
import com.tilldawn.View.LoginView;

public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    public static User currentUser;
    public static int killCount;
    public static int hitCount;
    public static int firedCount;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        getMain().setScreen(new LoginView(new LoginController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void saveGameProgress(int waveEnded) {
        if (currentUser != null) {
            currentUser.setKillCount(currentUser.getKillCount() + killCount);

            if (firedCount != 0) {
                float accuracy = (float) hitCount / firedCount * 100;
                currentUser.setAccuracy((int) accuracy);
            }

            currentUser.setLastRound(waveEnded);
            new UserManager().saveCurrentUser();
        }

        // Reset counters
        killCount = hitCount = firedCount = 0;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }

    public static int getKillCount() {
        return killCount;
    }

    public static void setKillCount(int killCount) {
        Main.killCount = killCount;
    }

    public static int getHitCount() {
        return hitCount;
    }

    public static void setHitCount(int hitCount) {
        Main.hitCount = hitCount;
    }

    public static int getFiredCount() {
        return firedCount;
    }

    public static void setFiredCount(int firedCount) {
        Main.firedCount = firedCount;
    }

}
