package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SaveData.User;
import com.tilldawn.Model.SaveData.UserManager;
import com.tilldawn.View.LoginView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.ProfileView;

import java.io.File;

public class ProfileController {
    private ProfileView view;
    private final UserManager userManager = new UserManager();
    private final Skin skin = GameAssetManager.getGameAssetManager().getSkin();

    public void setView(ProfileView view) {
        this.view = view;
        updateUserInfo();
        addListeners();
    }

    private void updateUserInfo() {
        User user = userManager.getCurrentUser();
        if (user == null) return;

        view.getUsernameLabel().setText("Username: " + user.getUsername());
        view.getScoreLabel().setText("Score: " + user.getScore());
        view.getKillsLabel().setText("Kills: " + user.getKillCount());
        view.getPlaytimeLabel().setText("Playtime: " + user.getLastRound() + "m");
        view.getAvatarImage().setDrawable(new Image(new Texture(user.getAvatarString())).getDrawable());
    }

    private void addListeners() {
        view.getBackButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

        view.getChangeUsernameButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showUsernameDialog();
            }
        });

        view.getChangePasswordButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showPasswordDialog();
            }
        });

        view.getChangeAvatarButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showAvatarDialog();
            }
        });

        view.getDeleteAccountButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                userManager.getUsers().removeIf(u -> u.getUsername().equals(Main.currentUser.getUsername()));
                userManager.logout();
                userManager.saveCurrentUser();
                Main.getMain().setScreen(new LoginView(new LoginController(), skin));
            }
        });
    }

    private void showUsernameDialog() {
        final TextField newUsername = new TextField("", skin);

        Dialog dialog = new Dialog("Change Username", skin) {
            @Override
            protected void result(Object object) {
                if ((Boolean) object) {
                    String newName = newUsername.getText().trim();
                    if (newName.isEmpty() || userManager.usernameExists(newName)) {
                        showError("Invalid or duplicate username");
                    } else {
                        Main.currentUser.setUsername(newName);
                        userManager.saveCurrentUser();
                        updateUserInfo();
                    }
                }
            }
        };

        dialog.text("Enter new username:");
        dialog.getContentTable().row();
        dialog.getContentTable().add(newUsername).width(200);
        dialog.button("OK", true).button("Cancel", false);
        dialog.show(view.getStage());
    }


    private void showPasswordDialog() {
        final TextField newPassword = new TextField("", skin);
        newPassword.setPasswordMode(true);
        newPassword.setPasswordCharacter('*');

        Dialog dialog = new Dialog("Change Password", skin) {
            @Override
            protected void result(Object object) {
                if ((Boolean) object) {
                    String pass = newPassword.getText();
                    if (!pass.matches("^(?=.*[@#$%&*)(_])(?=.*\\d)(?=.*[A-Z]).{8,}$")) {
                        showError("Password too weak");
                    } else {
                        Main.currentUser.setPassword(pass);
                        userManager.saveCurrentUser();
                    }
                }
            }
        };

        dialog.text("Enter new password:");
        dialog.getContentTable().row();
        dialog.getContentTable().add(newPassword).width(200);
        dialog.button("OK", true).button("Cancel", false);
        dialog.show(view.getStage());
    }


    private void showAvatarDialog() {
        Dialog dialog = new Dialog("Choose Avatar", skin);
        Table avatarTable = new Table();

        for (int i = 1; i <= 3; i++) {
            String path = "assets/Images/Avatars/" + i + ".png";
            Image img = new Image(new Texture(Gdx.files.internal(path)));
            img.setSize(64, 64);
            final int index = i;
            img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    Main.currentUser.setAvatarString(path);
                    userManager.saveCurrentUser();
                    updateUserInfo();
                    dialog.hide();
                }
            });
            avatarTable.add(img).pad(10);
        }

        dialog.getContentTable().add(avatarTable);
        dialog.button("Cancel");
        dialog.show(view.getStage());
    }

    private void showError(String message) {
        Dialog error = new Dialog("Error", skin);
        error.text(message).button("OK").show(view.getStage());
    }

    public void handleButtons() {
        // handled through listeners now
    }
}
