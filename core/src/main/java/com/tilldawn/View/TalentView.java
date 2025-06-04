package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.TalentController;

import java.util.Map;

public class TalentView implements Screen {
    private Stage stage;
    private Skin skin;
    private TalentController controller;
    private Table mainTable;

    public TalentView(TalentController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().pad(20);

        Label title = new Label("Talent Menu (Hint)", skin);
        title.setColor(Color.GOLD);
        mainTable.add(title).colspan(2).padBottom(30);
        mainTable.row();

        stage.addActor(mainTable);

        controller.loadData();

        // دکمه بازگشت
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(event -> {
            controller.onBackPressed();
            return true;
        });
        mainTable.row().padTop(40);
        mainTable.add(backButton).colspan(2).center();
    }

    public void showHeroes(String[] heroes) {
        Label hLabel = new Label("Heroes (min 3 of 5):", skin);
        hLabel.setColor(Color.CYAN);
        mainTable.add(hLabel).left().padBottom(10);
        mainTable.row();

        for (String hero : heroes) {
            Label heroLabel = new Label(hero, skin);
            mainTable.add(heroLabel).left().padLeft(20);
            mainTable.row();
        }
        mainTable.row().padTop(20);
    }

    public void showKeys(String[] keys) {
        Label kLabel = new Label("Game Keys (5):", skin);
        kLabel.setColor(Color.CYAN);
        mainTable.add(kLabel).left().padBottom(10);
        mainTable.row();

        for (String key : keys) {
            Label keyLabel = new Label(key, skin);
            mainTable.add(keyLabel).left().padLeft(20);
            mainTable.row();
        }
        mainTable.row().padTop(20);
    }

    public void showAbilities(Map<String, String> abilities) {
        Label aLabel = new Label("Abilities (5):", skin);
        aLabel.setColor(Color.CYAN);
        mainTable.add(aLabel).left().padBottom(10);
        mainTable.row();

        for (Map.Entry<String, String> entry : abilities.entrySet()) {
            Label abilityLabel = new Label(entry.getKey() + ": " + entry.getValue(), skin);
            mainTable.add(abilityLabel).left().padLeft(20);
            mainTable.row();
        }
        mainTable.row().padTop(20);
    }

    public void showCheats(String[] cheats) {
        Label cLabel = new Label("Cheat Codes (5):", skin);
        cLabel.setColor(Color.CYAN);
        mainTable.add(cLabel).left().padBottom(10);
        mainTable.row();

        for (String cheat : cheats) {
            Label cheatLabel = new Label(cheat, skin);
            mainTable.add(cheatLabel).left().padLeft(20);
            mainTable.row();
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() { stage.dispose(); }
}
