package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.PreGameMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class PreGameMenuView implements Screen {
    private Stage stage;
    private final Label titleLabel;
    private final TextButton startButton;
    private final TextButton backButton;
    private final SelectBox<String> heroSelectBox;
    private final SelectBox<String> weaponSelectBox;
    private final SelectBox<String> durationSelectBox;
    private final Image heroPreview;
    private final Image weaponPreview;
    private Table table;
    private PreGameMenuController controller;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        this.titleLabel = new Label("Game Setup", skin);
        this.startButton = new TextButton("Start Game", skin);
        this.backButton = new TextButton("Back", skin);
        this.heroSelectBox = new SelectBox<>(skin);
        this.weaponSelectBox = new SelectBox<>(skin);
        this.durationSelectBox = new SelectBox<>(skin);

        skin.add("default-hero", GameAssetManager.getGameAssetManager().getSmgTexture());
        this.heroPreview = new Image(skin.getDrawable("default-hero"));

        skin.add("default-hero", GameAssetManager.getGameAssetManager().getSmgTexture());
        this.weaponPreview = new Image(skin.getDrawable("default-hero"));


        // Setup options
        Array<String> heroes = new Array<>();
        heroes.add("Hero 1");
        heroes.add("Hero 2");
        heroes.add("Hero 3");
        heroSelectBox.setItems(heroes);

        Array<String> weapons = new Array<>();
        weapons.add("SMG");
        weapons.add("Shotgun");
        weapons.add("Rifle");
        weaponSelectBox.setItems(weapons);

        Array<String> durations = new Array<>();
        durations.add("2.5 minutes");
        durations.add("5 minutes");
        durations.add("10 minutes");
        durations.add("20 minutes");
        durationSelectBox.setItems(durations);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(titleLabel).colspan(2).padBottom(30);
        table.row();

        // Hero selection
        Table heroTable = new Table();
        heroTable.add(new Label("Select Hero:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        heroTable.add(heroSelectBox).width(200);
        table.add(heroTable).colspan(2).padBottom(20);
        table.row();
        table.add(heroPreview).size(150, 150).colspan(2).padBottom(20);
        table.row();

        // Weapon selection
        Table weaponTable = new Table();
        weaponTable.add(new Label("Select Weapon:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        weaponTable.add(weaponSelectBox).width(200);
        table.add(weaponTable).colspan(2).padBottom(20);
        table.row();
        table.add(weaponPreview).size(150, 50).colspan(2).padBottom(20);
        table.row();

        // Duration selection
        Table durationTable = new Table();
        durationTable.add(new Label("Game Duration:", GameAssetManager.getGameAssetManager().getSkin())).padRight(20);
        durationTable.add(durationSelectBox).width(200);
        table.add(durationTable).colspan(2).padBottom(30);
        table.row();

        // Buttons
        table.add(backButton).padRight(20);
        table.add(startButton);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    public TextButton getStartButton() {
        return startButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public SelectBox<String> getHeroSelectBox() {
        return heroSelectBox;
    }

    public SelectBox<String> getWeaponSelectBox() {
        return weaponSelectBox;
    }

    public SelectBox<String> getDurationSelectBox() {
        return durationSelectBox;
    }

    public Image getHeroPreview() {
        return heroPreview;
    }

    public Image getWeaponPreview() {
        return weaponPreview;
    }
}
