package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.SettingsController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class SettingsView implements Screen {
    private Stage stage;
    private final TextButton backButton;
    private final Slider volumeSlider;
    private final Slider musicSlider;
    private final CheckBox fullscreenCheckbox;
    private final CheckBox vSyncCheckbox;
    private final CheckBox autoReloadCheckbox;
    private final CheckBox grayscaleCheckbox;
    private final SelectBox<String> resolutionSelectBox;
    private Table table;
    private SettingsController controller;

    public SettingsView(SettingsController controller, Skin skin) {
        this.controller = controller;
        this.backButton = new TextButton("Back", skin);
        this.volumeSlider = new Slider(0, 100, 1, false, skin);
        this.musicSlider = new Slider(0, 100, 1, false, skin);
        this.fullscreenCheckbox = new CheckBox(" Fullscreen", skin);
        this.vSyncCheckbox = new CheckBox(" VSync", skin);
        this.autoReloadCheckbox = new CheckBox(" Auto Reload", skin);
        this.grayscaleCheckbox = new CheckBox(" Grayscale Mode", skin);
        this.resolutionSelectBox = new SelectBox<>(skin);

        // Set up resolution options
        String[] resolutions = {"800x600", "1024x768", "1280x720", "1366x768", "1920x1080"};
        resolutionSelectBox.setItems(resolutions);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(new Label("Settings", GameAssetManager.getGameAssetManager().getSkin())).colspan(2).padBottom(30);
        table.row();
        table.add(new Label("Sound Volume:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(volumeSlider).width(300).padBottom(10);
        table.row();
        table.add(new Label("Music Volume:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(musicSlider).width(300).padBottom(10);
        table.row();
        table.add(new Label("Resolution:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(resolutionSelectBox).width(300).padBottom(10);
        table.row();
        table.add(fullscreenCheckbox).colspan(2).padBottom(5);
        table.row();
        table.add(vSyncCheckbox).colspan(2).padBottom(5);
        table.row();
        table.add(autoReloadCheckbox).colspan(2).padBottom(5);
        table.row();
        table.add(grayscaleCheckbox).colspan(2).padBottom(20);
        table.row();
        table.add(backButton).colspan(2).width(200);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleButtons();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    // Other required Screen methods...

    public TextButton getBackButton() {
        return backButton;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public Slider getMusicSlider() {
        return musicSlider;
    }

    public CheckBox getFullscreenCheckbox() {
        return fullscreenCheckbox;
    }

    public CheckBox getVSyncCheckbox() {
        return vSyncCheckbox;
    }

    public CheckBox getAutoReloadCheckbox() {
        return autoReloadCheckbox;
    }

    public CheckBox getGrayscaleCheckbox() {
        return grayscaleCheckbox;
    }

    public SelectBox<String> getResolutionSelectBox() {
        return resolutionSelectBox;
    }
}
