package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SaveData.UserManager;
import com.tilldawn.View.LoginView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.RegistrationView;

public class RegistrationController {
    private RegistrationView view;
    private UserManager userManager;

    public RegistrationController() {
        this.userManager = new UserManager();
    }

    public void setView(RegistrationView view) {
        this.view = view;
    }

    public void handleButtons() {
        if (view != null) {
            if (view.getRegisterButton().isChecked()) {
                handleRegistration();
            }

            if (view.getBackButton().isChecked()) {
                returnToLoginScreen();
            }
        }
    }

    private void handleRegistration() {
        String username = view.getUsernameField().getText().trim();
        String password = view.getPasswordField().getText();
        String confirmPassword = view.getConfirmPasswordField().getText();
        String securityQuestion = view.getQuestionSelectBox().getSelected();
        String securityAnswer = view.getAnswerField().getText().trim();

        if (!validateInput(username, password, confirmPassword, securityAnswer)) {
            return;
        }

        if (userManager.register(username, password, securityQuestion, securityAnswer)) {
            onRegistrationSuccess(username, password);
        } else {
            view.getErrorLabel().setText("Username already exists");
        }
    }

    private boolean validateInput(String username, String password,
                                  String confirmPassword, String securityAnswer) {
        if (username.isEmpty() || password.isEmpty() || securityAnswer.isEmpty()) {
            view.getErrorLabel().setText("All fields are required");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            view.getErrorLabel().setText("Passwords don't match");
            return false;
        }

         String PASSWORD_PATTERN = "^(?=.*[@#$%&*)(_])(?=.*\\d)(?=.*[A-Z]).{8,}$";

        if (!password.matches(PASSWORD_PATTERN)) {
            view.getErrorLabel().setText("Password is weak!");
            return false;
        }

        return true;
    }

    private void onRegistrationSuccess(String username, String password) {
        view.getErrorLabel().setText("Registration successful!");
        view.getErrorLabel().setColor(0, 1, 0, 1); // Green for success

        // Auto-login after registration
        userManager.login(username, password);

        // Transition to main menu after delay
        Gdx.app.postRunnable(() -> {
            try {
                Thread.sleep(1000);
                returnToMainMenu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void returnToMainMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    private void returnToLoginScreen() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginView(new LoginController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
