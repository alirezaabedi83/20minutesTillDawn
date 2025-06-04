package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SaveData.UserManager;
import com.tilldawn.View.*;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleButtons() {
        if (view != null) {
            if (view.getPlayButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }

            if (view.getProfileButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileView(new ProfileController(), GameAssetManager.getGameAssetManager().getSkin()));
            }

            if (view.getSettingsButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SettingsView(new SettingsController(), GameAssetManager.getGameAssetManager().getSkin()));
            }

            if (view.getScoreboardButton().isChecked()) {
                ScoreboardController scoreboardController = new ScoreboardController(new UserManager());
                Main.getMain().setScreen(new ScoreboardView(scoreboardController ,GameAssetManager.getGameAssetManager().getSkin()));
            }

            if (view.getTalentButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new TalentView(
                        new TalentController(),
                        GameAssetManager.getGameAssetManager().getSkin()
                ));
            }

            if (view.getLogoutButton().isChecked()) {
                Main.setCurrentUser(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginView(new LoginController(), GameAssetManager.getGameAssetManager().getSkin()));

            }

            if (view.getExitButton().isChecked()) {
                Gdx.app.exit();
            }
        }
    }
}
