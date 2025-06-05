package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Control.LoginController;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SaveData.User;
import com.tilldawn.View.LoginView;
import com.badlogic.gdx.audio.Music;

public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    public static User currentUser;
    private Music backgroundMusic;
    public static int killCount;
    public static int hitCount;
    public static int firedCount;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/1.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(1.0f);
        backgroundMusic.play();
        getMain().setScreen(new LoginView(new LoginController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
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

    public static Music getBackgroundMusic() {
        return main.backgroundMusic;
    }

    public void setBackgroundMusic(Music music) {
        this.backgroundMusic = music;
    }



}
