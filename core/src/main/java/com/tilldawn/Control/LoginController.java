package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SaveData.UserManager;
import com.tilldawn.View.ForgotPasswordView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.RegistrationView;
import com.tilldawn.View.LoginView;

public class LoginController {
    private LoginView view;
    private UserManager userManager = new UserManager();

    public void setView(LoginView view) {
        this.view = view;
    }

    public void handleButtons() {
        if (view != null) {
            if (view.getLoginButton().isChecked()) {
                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();

                if (userManager.login(username, password)) {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
                        GameAssetManager.getGameAssetManager().getSkin()));
                } else {
                    view.getErrorLabel().setText("Invalid username or password");
                }
            }

            if (view.getRegisterButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegistrationView(new RegistrationController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if (view.getGuestButton().isChecked()) {
                Main.currentUser = null; // Clear any user session
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if (view.getForgotPasswordButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ForgotPasswordView(
                    new ForgotPasswordController(),
                    GameAssetManager.getGameAssetManager().getSkin()
                ));
            }
        }
    }
}
