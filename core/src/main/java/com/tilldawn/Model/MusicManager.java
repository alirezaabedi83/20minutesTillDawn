package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
    private static Music backgroundMusic;

    public static void load() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/1.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(GameSettings.getInstance().getMusicVolume());
    }

    public static void play() {
        if (!backgroundMusic.isPlaying()) backgroundMusic.play();
    }

    public static void pause() {
        if (backgroundMusic.isPlaying()) backgroundMusic.pause();
    }

    public static void stop() {
        backgroundMusic.stop();
    }

    public static void updateVolume() {
        backgroundMusic.setVolume(GameSettings.getInstance().getMusicVolume());
    }

    public static boolean isPlaying() {
        return backgroundMusic.isPlaying();
    }
}
