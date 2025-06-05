package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.Control.GameController;
import com.tilldawn.Model.AbilityType;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;

public class PauseMenuView extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final Table table;
    private final Player player;
    private final GameController controller;

    private final String[] cheats = {
            "T : Reduce remaining game time by 1 minute",
            "Y : Add character level",
            "U : Extra health if empty",
            "I : Go to boss fight"
    };

    public PauseMenuView(Player player, GameController controller) {
        this.player = player;
        this.controller = controller;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        addButtons();
    }

    private void addButtons() {
        table.clear();

        Label title = new Label("Paused", skin);
        table.add(title).colspan(2).padBottom(30);
        table.row();

        // Resume
        TextButton resumeButton = new TextButton("Resume", skin);
        resumeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Game.getInstance().setPaused(false);
                Main.getMain().setScreen(controller.getView());
            }
        });
        table.add(resumeButton).pad(10).width(220);
        table.row();

        TextButton cheatsButton = new TextButton("Show Cheats", skin);
        cheatsButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Dialog dialog = new Dialog("Cheat Codes", skin);
                for (String cheat : cheats) {
                    dialog.text(cheat + "\n");
                }
                dialog.button("OK");
                dialog.show(stage);
            }
        });
        table.add(cheatsButton).pad(10).width(220);
        table.row();

        TextButton abilitiesButton = new TextButton("Show Abilities", skin);
        abilitiesButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Dialog dialog = new Dialog("Abilities", skin);
                if (player.getAbilities().isEmpty()) {
                    dialog.text("No abilities yet.");
                } else {
                    for (AbilityType type : player.getAbilities().keySet()) {
                        dialog.text("- " + type.name() + "\n");
                    }
                }
                dialog.button("OK");
                dialog.show(stage);
            }
        });
        table.add(abilitiesButton).pad(10).width(220);
        table.row();

        TextButton bwButton = new TextButton("Toggle Black & White", skin);
        bwButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Game.getInstance().toggleBWMode();
            }
        });
        table.add(bwButton).pad(10).width(220);
        table.row();

        TextButton giveUpButton = new TextButton("Give Up", skin);
        giveUpButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Game.getInstance().setGameOver(true);
                Game.getInstance().setPaused(false);
                controller.gameOver(false);
            }
        });
        table.add(giveUpButton).pad(10).width(220);
        table.row();

        TextButton saveExitButton = new TextButton("Save and Exit", skin);
        saveExitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Game.getInstance().saveGame();
                Gdx.app.exit();
            }
        });
        table.add(saveExitButton).pad(10).width(220);
        table.row();
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
