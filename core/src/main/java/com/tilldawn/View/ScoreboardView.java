package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ScoreboardController;
import com.tilldawn.Model.SaveData.User;

import java.util.List;

public class ScoreboardView implements Screen {
    private Stage stage;
    private Skin skin;
    private ScoreboardController controller;
    private List<User> users;
    private User currentUser;

    private Table buttonTable;
    private Table dataTable;

    public ScoreboardView(ScoreboardController controller, Skin skin) {
        this.skin = skin;
        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // جدول دکمه‌ها
        buttonTable = new Table();
        buttonTable.top().padTop(30);

        TextButton sortByScore = new TextButton("Sort by Score", skin);
        TextButton sortByUsername = new TextButton("Sort by Username", skin);
        TextButton sortByKills = new TextButton("Sort by Kills", skin);
        TextButton sortByTime = new TextButton("Sort by Time Alive", skin);
        TextButton backButton = new TextButton("Back", skin);

        sortByScore.addListener(e -> { controller.loadAndRender("score"); return true; });
        sortByUsername.addListener(e -> { controller.loadAndRender("username"); return true; });
        sortByKills.addListener(e -> { controller.loadAndRender("kill"); return true; });
        sortByTime.addListener(e -> { controller.loadAndRender("time"); return true; });
        backButton.addListener(e -> {
            controller.backToMainMenu();
            return true;
        });

        buttonTable.add(sortByScore).pad(5);
        buttonTable.add(sortByUsername).pad(5);
        buttonTable.add(sortByKills).pad(5);
        buttonTable.add(sortByTime).pad(5);
        buttonTable.row().padTop(15);
        buttonTable.add(backButton).colspan(4).padBottom(30);

        dataTable = new Table();

        mainTable.top();
        mainTable.add(buttonTable).expandX().fillX().row();
        mainTable.add(dataTable).expand().fill();

        controller.loadAndRender("score");
    }

    public void setEntries(List<User> users, User currentUser) {
        this.users = users;
        this.currentUser = currentUser;
        renderEntries();
    }

    private void renderEntries() {
        if (dataTable == null) return;

        dataTable.clearChildren();

        Label title = new Label("Top Players", skin);
        title.setColor(Color.GOLD);
        dataTable.add(title).colspan(5).padBottom(20);
        dataTable.row();

        dataTable.add(new Label("Rank", skin)).pad(10);
        dataTable.add(new Label("Username", skin)).pad(10);
        dataTable.add(new Label("Score", skin)).pad(10);
        dataTable.add(new Label("Kills", skin)).pad(10);
        dataTable.add(new Label("Alive Time", skin)).pad(10);
        dataTable.row();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Label rankLabel = new Label(String.valueOf(i + 1), skin);
            Label nameLabel = new Label(user.getUsername(), skin);
            Label scoreLabel = new Label(String.valueOf(user.getScore()), skin);
            Label killLabel = new Label(String.valueOf(user.getKillCount()), skin);
            Label timeLabel = new Label(user.getTimeAlive() + " s", skin);

            if (i == 0) nameLabel.setColor(Color.GOLD);
            else if (i == 1) nameLabel.setColor(Color.GOLD);
            else if (i == 2) nameLabel.setColor(Color.BROWN);

            if (currentUser != null && user.getUsername().equals(currentUser.getUsername())) {
                nameLabel.setColor(Color.GREEN);
                scoreLabel.setColor(Color.GREEN);
                killLabel.setColor(Color.GREEN);
                timeLabel.setColor(Color.GREEN);
            }

            dataTable.add(rankLabel).pad(5);
            dataTable.add(nameLabel).pad(5);
            dataTable.add(scoreLabel).pad(5);
            dataTable.add(killLabel).pad(5);
            dataTable.add(timeLabel).pad(5);
            dataTable.row();
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }

    public void setController(ScoreboardController controller) {
        this.controller = controller;
    }
}
