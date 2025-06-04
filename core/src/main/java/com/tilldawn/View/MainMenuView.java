package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Main;

public class MainMenuView implements Screen {
    private Stage stage;
    private final TextButton playButton;
    private final TextButton profileButton;
    private final TextButton settingsButton;
    private final TextButton scoreboardButton;
    private final TextButton talentButton;
    private final TextButton logoutButton;
    private final TextButton loadButton;



    private final TextButton exitButton;
    private final Label welcomeLabel;
    private final Image avatarImage;
    private Table table;
    private MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.playButton = new TextButton("Play", skin);
        this.profileButton = new TextButton("Profile", skin);
        this.settingsButton = new TextButton("Settings", skin);
        this.scoreboardButton = new TextButton("Scoreboard", skin);
        this.talentButton = new TextButton("Talents", skin);
        this.logoutButton=new TextButton("logout",skin);
        this.loadButton=new TextButton("load game",skin);
        this.exitButton = new TextButton("Exit", skin);
        if (Main.getCurrentUser()==null)this.welcomeLabel = new Label("Welcome, guest" +" ,score: " +Main.getKillCount() , skin);
        else this.welcomeLabel = new Label("Welcome," + Main.getCurrentUser().getUsername() +"score: " +Main.getKillCount(), skin);
        this.welcomeLabel.setColor(Color.YELLOW);

        Texture avatarTexture;
        if (Main.getCurrentUser()==null)avatarTexture = new Texture(Gdx.files.internal("/home/alireza/20minutesTillDawn/assets/Images/Avatars/3.png"));
        else avatarTexture = new Texture(Gdx.files.internal(Main.getCurrentUser().getAvatarString()));
        skin.add("default-avatar", avatarTexture);
        this.avatarImage = new Image(skin.getDrawable("default-avatar"));

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);

        // Top section with avatar and welcome
        Table topTable = new Table();
        topTable.add(avatarImage).size(100, 100).padRight(20);
        topTable.add(welcomeLabel);

        // Main menu buttons
        Table buttonTable = new Table();
        buttonTable.defaults().width(200).height(60).pad(10);
        buttonTable.add(playButton).row();
        buttonTable.add(profileButton).row();
        buttonTable.add(settingsButton).row();
        buttonTable.add(scoreboardButton).row();
        buttonTable.add(talentButton).row();
        buttonTable.add(logoutButton).row();
        buttonTable.add(loadButton).row();

        buttonTable.add(exitButton).row();

        // Combine tables
        table.add(topTable).padBottom(50).row();
        table.add(buttonTable);

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

    public TextButton getPlayButton() {
        return playButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getSettingsButton() {
        return settingsButton;
    }

    public TextButton getScoreboardButton() {
        return scoreboardButton;
    }

    public TextButton getTalentButton() {
        return talentButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public Label getWelcomeLabel() {
        return welcomeLabel;
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }
}
