package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.Weapon;
import com.tilldawn.View.GameView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PreGameMenuView;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PreGameMenuController {
    private PreGameMenuView view;

    public void setView(PreGameMenuView view) {
        this.view = view;
        setupListeners();
    }

    private void setupListeners() {
        view.getStartButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String heroName = view.getHeroSelectBox().getSelected();
                String weaponName = view.getWeaponSelectBox().getSelected();
                String duration = view.getDurationSelectBox().getSelected();

                Player player = new Player(heroName);
                Game.getInstance().setCurrentPlayer(player);

                Weapon weapon = new Weapon(weaponName);
                player.setWeapon(weapon);

                float durationInSec =0;
                switch (duration) {
                    case "2.5 minutes" :durationInSec = 150;
                    break;
                    case "5 minutes" :durationInSec = 300;
                    break;
                    case "10 minutes" :durationInSec = 600;
                    break;
                    case "20 minutes" :durationInSec = 1200;
                    break;
                    default :durationInSec = 1200;
                };
                Game.getInstance().setTotalGameTime(durationInSec);

                GameController controller = new GameController();
                Main.getMain().setScreen(new GameView(controller, GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        view.getBackButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }
}
