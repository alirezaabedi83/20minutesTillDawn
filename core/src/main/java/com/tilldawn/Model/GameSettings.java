package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.tilldawn.Main;

public class GameSettings {
    private static GameSettings instance;
    private float musicVolume = 0.5f;
    private boolean sfxEnabled = true;
    private boolean grayscale = true;
    private transient GrayscaleShader grayscaleShader;
    private float grayscaleIntensity = 1f;
    private String keyUp = "W";
    private String keyDown = "S";
    private String keyLeft = "A";
    private String keyRight = "D";

    private String currentTrackName = "Track 1";
    private Music currentMusic = Main.getBackgroundMusic();
    private boolean autoReload = false;

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }


    private GameSettings() {
        loadMusic(currentTrackName);
        grayscaleShader = new GrayscaleShader();
    }

    public static GameSettings getInstance() {
        if (instance == null)
            instance = new GameSettings();
        return instance;
    }

    public Music loadMusic(String name) {
        if (currentMusic != null) currentMusic.dispose();
        String fileName = "";

        switch (name) {
            case "Track 1": fileName = "Music/1.mp3"; break;
            case "Track 2": fileName = "Music/2.mp3"; break;
            case "Track 3": fileName = "Music/3.mp3"; break;
        }

        FileHandle file = Gdx.files.internal(fileName);
        currentMusic = Gdx.audio.newMusic(file);
        currentTrackName = name;
        return currentMusic;
    }

    public float getMusicVolume() { return musicVolume; }
    public void setMusicVolume(float volume) { this.musicVolume = volume; }

    public boolean isSfxEnabled() { return sfxEnabled; }
    public void setSfxEnabled(boolean enabled) { this.sfxEnabled = enabled; }

    public boolean isGrayscale() { return grayscale; }
    public void setGrayscale(boolean grayscale) {
        this.grayscale = grayscale;
    }

    public float getGrayscaleIntensity() { return grayscaleIntensity; }
    public void setGrayscaleIntensity(float intensity) {
        this.grayscaleIntensity = intensity;
    }

    public GrayscaleShader getGrayscaleShader() {
        return grayscaleShader;
    }

    public String getKeyUp() { return keyUp; }
    public void setKeyUp(String keyUp) { this.keyUp = keyUp; }

    public String getKeyDown() { return keyDown; }
    public void setKeyDown(String keyDown) { this.keyDown = keyDown; }

    public String getKeyLeft() { return keyLeft; }
    public void setKeyLeft(String keyLeft) { this.keyLeft = keyLeft; }

    public String getKeyRight() { return keyRight; }
    public void setKeyRight(String keyRight) { this.keyRight = keyRight; }

    public String getCurrentTrackName() { return currentTrackName; }
    public Music getCurrentMusic() { return currentMusic; }
}
