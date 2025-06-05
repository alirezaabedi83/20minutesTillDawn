package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.SaveData.User;


public class GameOverView extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final boolean won;
    private final Player player;
    private final int timeAlive;
    private final int kills;
    private final int score;

    public GameOverView(boolean won, Player player, float totalTimeSeconds) {
        this.won = won;
        this.player = player;
        this.timeAlive = (int) totalTimeSeconds;
        this.kills = player.getKills();
        this.score = timeAlive * kills;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        String status = won ? "🎉 You Survived!" : "☠ Game Over ☠";

        Label title = new Label(status, skin);
        title.setFontScale(2f);
        table.add(title).padBottom(30).row();

        User currentUser = Main.currentUser;
        if (currentUser == null) {
            table.add(new Label("Username: guest" , skin)).pad(10).row();
        }else   table.add(new Label("Username: " + currentUser.getUsername(), skin)).pad(10).row();

        table.add(new Label("Time Alive: " + timeAlive + " seconds", skin)).pad(10).row();
        table.add(new Label("Kills: " + kills, skin)).pad(10).row();
        table.add(new Label("Score: " + score, skin)).pad(10).row();

        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
        table.add(exitButton).padTop(40).width(200);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
