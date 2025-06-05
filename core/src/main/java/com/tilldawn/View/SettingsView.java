package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.SettingsController;
import com.tilldawn.Model.GameAssetManager;

public class SettingsView implements Screen {
    private Stage stage;
    private Table table;
    private final SettingsController controller;

    private final TextButton backButton;
    private final Slider musicVolumeSlider;
    private final CheckBox sfxToggle;
    private final CheckBox grayscaleToggle;
    private final SelectBox<String> musicSelectBox;
    private final CheckBox autoReloadToggle;


    private final TextField upKeyField;
    private final TextField downKeyField;
    private final TextField leftKeyField;
    private final TextField rightKeyField;

    public SettingsView(SettingsController controller, Skin skin) {
        this.controller = controller;

        this.backButton = new TextButton("Back", skin);
        this.musicVolumeSlider = new Slider(0, 100, 1, false, skin);
        this.sfxToggle = new CheckBox(" Enable SFX", skin);
        this.grayscaleToggle = new CheckBox(" Grayscale Mode", skin);
        this.autoReloadToggle = new CheckBox(" Auto Reload", skin);


        this.musicSelectBox = new SelectBox<>(skin);
        musicSelectBox.setItems("Track 1", "Track 2", "Track 3");

        this.upKeyField = new TextField("W", skin);
        this.downKeyField = new TextField("S", skin);
        this.leftKeyField = new TextField("A", skin);
        this.rightKeyField = new TextField("D", skin);

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

        table.add(new Label("Music Volume:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(musicVolumeSlider).width(300).padBottom(15);
        table.row();

        table.add(new Label("Music Track:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(musicSelectBox).width(200).padBottom(15);
        table.row();

        table.add(sfxToggle).colspan(2).padBottom(15);
        table.row();

        table.add(grayscaleToggle).colspan(2).padBottom(20);
        table.row();

        table.add(autoReloadToggle).colspan(2).padBottom(10);
        table.row();


        table.add(new Label("Up Key:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(upKeyField).width(100).padBottom(10);
        table.row();
        table.add(new Label("Down Key:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(downKeyField).width(100).padBottom(10);
        table.row();
        table.add(new Label("Left Key:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(leftKeyField).width(100).padBottom(10);
        table.row();
        table.add(new Label("Right Key:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        table.add(rightKeyField).width(100).padBottom(30);
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

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    public TextButton getBackButton() { return backButton; }
    public Slider getMusicVolumeSlider() { return musicVolumeSlider; }
    public CheckBox getSfxToggle() { return sfxToggle; }
    public CheckBox getGrayscaleToggle() { return grayscaleToggle; }
    public SelectBox<String> getMusicSelectBox() { return musicSelectBox; }
    public TextField getUpKeyField() { return upKeyField; }
    public TextField getDownKeyField() { return downKeyField; }
    public TextField getLeftKeyField() { return leftKeyField; }
    public TextField getRightKeyField() { return rightKeyField; }
    public CheckBox getAutoReloadToggle() {
        return autoReloadToggle;
    }

}
