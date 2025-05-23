package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.LoginController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class LoginView implements Screen {
    private Stage stage;
    private final TextButton loginButton;
    private final TextButton registerButton;
    private final TextButton guestButton;
    private final TextField usernameField;
    private final TextField passwordField;
    private final Label errorLabel;
    private final Label titleLabel;
    private Table table;
    private final TextButton forgotPasswordButton;
    private LoginController controller;

    public LoginView(LoginController controller, Skin skin) {
        this.controller = controller;
        this.loginButton = new TextButton("Login", skin);
        this.registerButton = new TextButton("Register", skin);
        this.guestButton = new TextButton("Play as Guest", skin);
        this.usernameField = new TextField("", skin);
        this.passwordField = new TextField("", skin);
        this.passwordField.setPasswordMode(true);
        this.passwordField.setPasswordCharacter('*');
        this.errorLabel = new Label("", skin);
        this.errorLabel.setColor(1, 0, 0, 1);
        this.titleLabel = new Label("Login", skin);
        this.forgotPasswordButton = new TextButton("Forgot Password?", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(titleLabel).colspan(2).padBottom(20);
        table.row();
        table.add(new Label("Username:", GameAssetManager.getGameAssetManager().getSkin())).padRight(10);
        table.add(usernameField).width(300).padBottom(10);
        table.row();
        table.add(new Label("Password:", GameAssetManager.getGameAssetManager().getSkin())).padRight(10);
        table.add(passwordField).width(300).padBottom(10);
        table.row();
        table.add(errorLabel).colspan(2).padBottom(20);
        table.row();
        table.add(registerButton).padRight(20);
        table.add(loginButton);
        table.row();
        table.add(guestButton).colspan(2).padTop(20);
        table.row();
        table.add(forgotPasswordButton).colspan(2).padTop(10);

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

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getGuestButton() {
        return guestButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextButton getForgotPasswordButton() {
        return forgotPasswordButton;
    }
}
