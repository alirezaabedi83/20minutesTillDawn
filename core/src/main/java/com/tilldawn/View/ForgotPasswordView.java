package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ForgotPasswordController;
import com.tilldawn.Model.GameAssetManager;

public class ForgotPasswordView implements Screen {
    private Stage stage;
    private final TextButton submitButton;
    private final TextButton backButton;
    private final TextField usernameField;
    private final TextField answerField;
    private final TextField newPasswordField;
    private final Label questionLabel;
    private final Label errorLabel;
    private final Label titleLabel;
    private Table table;
    private ForgotPasswordController controller;

    public ForgotPasswordView(ForgotPasswordController controller, Skin skin) {
        this.controller = controller;
        this.submitButton = new TextButton("Reset Password", skin);
        this.backButton = new TextButton("Back", skin);
        this.usernameField = new TextField("", skin);
        this.answerField = new TextField("", skin);
        this.newPasswordField = new TextField("", skin);
        this.newPasswordField.setPasswordMode(true);
        this.newPasswordField.setPasswordCharacter('*');
        this.questionLabel = new Label("", skin);
        this.errorLabel = new Label("", skin);
        this.errorLabel.setColor(1, 0, 0, 1);
        this.titleLabel = new Label("Password Recovery", skin);

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
        table.add(questionLabel).colspan(2).padBottom(10);
        table.row();
        table.add(new Label("Security Answer:", GameAssetManager.getGameAssetManager().getSkin())).padRight(10);
        table.add(answerField).width(300).padBottom(10);
        table.row();
        table.add(new Label("New Password:", GameAssetManager.getGameAssetManager().getSkin())).padRight(10);
        table.add(newPasswordField).width(300).padBottom(20);
        table.row();
        table.add(errorLabel).colspan(2).padBottom(20);
        table.row();
        table.add(backButton).padRight(20);
        table.add(submitButton);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleButtons();
    }

    // Other required methods
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() { stage.dispose(); }

    public TextField getUsernameField() { return usernameField; }
    public TextField getAnswerField() { return answerField; }
    public TextField getNewPasswordField() { return newPasswordField; }
    public Label getQuestionLabel() { return questionLabel; }
    public Label getErrorLabel() { return errorLabel; }
    public TextButton getSubmitButton() { return submitButton; }
    public TextButton getBackButton() { return backButton; }
}
