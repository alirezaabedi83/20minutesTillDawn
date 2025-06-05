package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.AbilityType;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;

import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AbilitySelectionView extends ScreenAdapter {
    private Stage stage;
    private Player player;
    private GameController gameController;
    private List<AbilityType> randomAbilities;

    public AbilitySelectionView(Player player, GameController gameController) {
        this.player = player;
        this.gameController = gameController;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        randomAbilities = getRandomAbilities();

        for (AbilityType type : randomAbilities) {
            TextButton button = new TextButton(type.name(), skin);
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Ability ability = new Ability(type);
                    ability.activate(player);
                    player.getAbilities().put(type, ability);
                    Game.getInstance().setPaused(false);

                    Main.getMain().setScreen(gameController.getView());
                }
            });
            table.row().pad(20);
            table.add(button).width(200);
        }

        stage.addActor(table);
    }

    private List<AbilityType> getRandomAbilities() {
        List<AbilityType> all = new ArrayList<>(Arrays.asList(AbilityType.values()));
        Collections.shuffle(all);
        return all.subList(0, 3);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

