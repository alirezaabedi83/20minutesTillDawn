package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SaveData.User;
import com.tilldawn.Model.SaveData.UserManager;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.ScoreboardView;

import java.util.Comparator;
import java.util.List;

public class ScoreboardController {
    private ScoreboardView view;
    private UserManager userManager;

    public ScoreboardController(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setView(ScoreboardView view) {
        this.view = view;
        view.setController(this);
        loadAndRender("score"); // پیش‌فرض مرتب‌سازی
    }

    public void loadAndRender(String sortBy) {
        List<User> users = userManager.getUsers();
        if (users == null || users.isEmpty()) return;

        switch (sortBy.toLowerCase()) {
            case "username":
                users.sort(Comparator.comparing(User::getUsername));
                break;
            case "kill":
                users.sort((u1, u2) -> Integer.compare(u2.getKillCount(), u1.getKillCount()));
                break;
            case "time":
                users.sort((u1, u2) -> Float.compare(u2.getTimeAlive(), u1.getTimeAlive()));
                break;
            case "score":
            default:
                users.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));
                break;
        }

        view.setEntries(users, userManager.getCurrentUser());
    }

    public void backToMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(
                new MainMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()
        ));
    }
}
