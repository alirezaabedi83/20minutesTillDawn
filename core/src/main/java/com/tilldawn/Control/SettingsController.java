package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.GameSettings;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.SettingsView;

public class SettingsController {
    private SettingsView view;
    private Music currentMusic;

    public void setView(SettingsView view) {
        this.view = view;

        currentMusic = GameSettings.getInstance().getCurrentMusic();
        if (currentMusic != null) {
            currentMusic.setVolume(GameSettings.getInstance().getMusicVolume());
            if (!currentMusic.isPlaying()) currentMusic.play();
        }
    }

    public void handleButtons() {

        float newVolume = view.getMusicVolumeSlider().getValue() / 100f;
        GameSettings.getInstance().setMusicVolume(newVolume);

        Music bgMusic = Main.getBackgroundMusic();
        if (bgMusic != null) {
            bgMusic.setVolume(newVolume);
        }

        // SFX
        GameSettings.getInstance().setSfxEnabled(view.getSfxToggle().isChecked());

        // Grayscale
        GameSettings.getInstance().setGrayscale(view.getGrayscaleToggle().isChecked());

        // کلیدها
        GameSettings.getInstance().setKeyUp(view.getUpKeyField().getText());
        GameSettings.getInstance().setKeyDown(view.getDownKeyField().getText());
        GameSettings.getInstance().setKeyLeft(view.getLeftKeyField().getText());
        GameSettings.getInstance().setKeyRight(view.getRightKeyField().getText());


        String selectedTrack = view.getMusicSelectBox().getSelected();
        if (!GameSettings.getInstance().getCurrentTrackName().equals(selectedTrack)) {
            if (bgMusic != null) {
                bgMusic.stop();
                bgMusic.dispose();
            }

            Music newMusic = GameSettings.getInstance().loadMusic(selectedTrack);
            newMusic.setLooping(true);
            newMusic.setVolume(GameSettings.getInstance().getMusicVolume());
            newMusic.play();

            Main.getMain().setBackgroundMusic(newMusic);
        }

        GameSettings.getInstance().setAutoReload(view.getAutoReloadToggle().isChecked());


        if (view.getBackButton().isPressed()) {
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin())); // یا هر منویی که دارید
        }
    }
}
