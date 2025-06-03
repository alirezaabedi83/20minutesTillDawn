package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ProfileController;
import com.tilldawn.Main;

public class ProfileView implements Screen {
    private Stage stage;
    private final TextButton backButton;
    private final TextButton changeUsernameButton;
    private final TextButton changePasswordButton;
    private final TextButton changeAvatarButton;
    private final TextButton deleteAccountButton;
    private final Label usernameLabel;
    private final Label scoreLabel;
    private final Label killsLabel;
    private final Label playtimeLabel;
    private final Image avatarImage;
    private Table table;
    private ProfileController controller;

    public ProfileView(ProfileController controller, Skin skin) {
        this.controller = controller;
        this.backButton = new TextButton("Back", skin);
        this.changeUsernameButton = new TextButton("Change Username", skin);
        this.changePasswordButton = new TextButton("Change Password", skin);
        this.changeAvatarButton = new TextButton("Change Avatar", skin);
        this.deleteAccountButton = new TextButton("Delete Account", skin);
        this.usernameLabel = new Label("Username: Guest", skin);
        this.scoreLabel = new Label("Score: 0", skin);
        this.killsLabel = new Label("Kills: 0", skin);
        this.playtimeLabel = new Label("Playtime: 0m", skin);
        this.avatarImage = new Image(skin.getDrawable("default-avatar"));

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);

        // Profile info section
        Table infoTable = new Table();
        infoTable.add(avatarImage).size(150, 150).padRight(20);

        Table statsTable = new Table();
        statsTable.add(usernameLabel).left().row();
        statsTable.add(scoreLabel).left().row();
        statsTable.add(killsLabel).left().row();
        statsTable.add(playtimeLabel).left();

        infoTable.add(statsTable);

        // Buttons section
        Table buttonTable = new Table();
        buttonTable.defaults().width(200).height(50).pad(5);
        buttonTable.add(changeUsernameButton).row();
        buttonTable.add(changePasswordButton).row();
        buttonTable.add(changeAvatarButton).row();
        buttonTable.add(deleteAccountButton).row();

        // Combine tables
        table.add(infoTable).padBottom(30).row();
        table.add(buttonTable).row();
        table.add(backButton).width(200).padTop(20);

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

    public TextButton getChangeUsernameButton() {
        return changeUsernameButton;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getChangeAvatarButton() {
        return changeAvatarButton;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Label getKillsLabel() {
        return killsLabel;
    }

    public Label getPlaytimeLabel() {
        return playtimeLabel;
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
