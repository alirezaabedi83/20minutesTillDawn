package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.RegistrationController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class RegistrationView implements Screen {
    private Stage stage;
    private final TextButton registerButton;
    private final TextButton backButton;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField confirmPasswordField;
    private final Label errorLabel;
    private final Label titleLabel;
    private Table table;
    private RegistrationController controller;
    private final SelectBox<String> questionSelectBox;
    private final TextField answerField;

    public RegistrationView(RegistrationController controller, Skin skin) {
        this.controller = controller;
        this.registerButton = new TextButton("Register", skin);
        this.backButton = new TextButton("Back", skin);
        this.usernameField = new TextField("", skin);
        this.passwordField = new TextField("", skin);
        this.passwordField.setPasswordMode(true);
        this.passwordField.setPasswordCharacter('*');
        this.confirmPasswordField = new TextField("", skin);
        this.confirmPasswordField.setPasswordMode(true);
        this.confirmPasswordField.setPasswordCharacter('*');
        this.errorLabel = new Label("", skin);
        this.errorLabel.setColor(1, 0, 0, 1); // Red color for errors
        this.titleLabel = new Label("Register New Account", skin);
        String[] questions = {
            "What was your first pet's name?",
            "What was your elementary school name?",
            "What city were you born in?",
            "What was your childhood best friend's name?"
        };
        this.questionSelectBox = new SelectBox<>(skin);
        this.questionSelectBox.setItems(questions);
        this.answerField = new TextField("", skin);


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
        table.add(new Label("Confirm Password:", GameAssetManager.getGameAssetManager().getSkin())).padRight(10);
        table.add(confirmPasswordField).width(300).padBottom(20);
        table.row();
        table.add(errorLabel).colspan(2).padBottom(20);
        table.row();
        table.add(backButton).padRight(20);
        table.add(registerButton);
        table.add(new Label("Security Question:", GameAssetManager.getGameAssetManager().getSkin())).padRight(10);
        table.add(questionSelectBox).width(300).padBottom(10);
        table.row();
        table.add(new Label("Answer:", GameAssetManager.getGameAssetManager().getSkin())).padRight(10);
        table.add(answerField).width(300).padBottom(10);
        table.row();

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

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public SelectBox<String> getQuestionSelectBox() {
        return questionSelectBox;
    }

    public TextField getAnswerField() {
        return answerField;
    }
}
