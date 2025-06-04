package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.GameSettings;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.TalentView;

import java.util.HashMap;
import java.util.Map;

public class TalentController {
    private TalentView view;

    // داده‌های هاردکد شده
    private final String[] heroes = {
            "Shana - HP: 4, Speed: 4 ",
            "Diamond - HP: 7, Speed: 1 ",
            "Scarlet - HP: 3, Speed: 5",
            "Lilith - HP: 5, Speed: 3",
            "Dasher - HP: 2, Speed: 10"
    };

    private final String[] keys = {
            GameSettings.getInstance().getKeyUp()+" - Move Up",
            GameSettings.getInstance().getKeyDown()+" - Move Down",
            GameSettings.getInstance().getKeyLeft()+" - Move Left",
            GameSettings.getInstance().getKeyRight()+" - Move Right",
            "Space - Attack"
    };

    private final String[] cheats = {
            "T :Reduce remaining game time by 1 minute",
            "Y: Add character level",
            "U: Extra health if empty",
            "I: Go to boss fight",
    };

    private final Map<String, String> abilities = new HashMap<>();

    public TalentController() {
        abilities.put("Vitality", "Increase max HP by 1 unit");
        abilities.put("Damager", "Increase weapon damage by 25% for 10 seconds");
        abilities.put("ProcRease", "Increase projectile by 1 unit");
        abilities.put("AmoCrease", "Increase max ammo by 5 units");
        abilities.put("Speedy", "Double player movement speed for 10 seconds");
    }

    public void setView(TalentView view) {
        this.view = view;
    }

    public void loadData() {
        view.showHeroes(heroes);
        view.showKeys(keys);
        view.showAbilities(abilities);
        view.showCheats(cheats);
    }

    public void onBackPressed() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(
                new MainMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()
        ));
    }
}
