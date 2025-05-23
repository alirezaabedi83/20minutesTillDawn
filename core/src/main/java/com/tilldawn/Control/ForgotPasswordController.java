package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.SaveData.UserManager;
import com.tilldawn.View.ForgotPasswordView;
import com.tilldawn.View.LoginView;

public class ForgotPasswordController {
    private ForgotPasswordView view;
    private UserManager userManager = new UserManager();

    public void setView(ForgotPasswordView view) {
        this.view = view;
    }

    public void handleButtons() {
        if (view != null) {
            if (view.getSubmitButton().isChecked()) {
                handlePasswordReset();
            }

            if (view.getBackButton().isChecked()) {
                returnToLoginScreen();
            }

            // Handle username field changes to load security question
            if (!view.getUsernameField().getText().isEmpty()) {
                handleUsernameInput();
            }
        }
    }

    private void handleUsernameInput() {
        String username = view.getUsernameField().getText();
        String question = userManager.getSecurityQuestion(username);

        if (question != null) {
            view.getQuestionLabel().setText(question);
        } else {
            view.getErrorLabel().setText("Username not found");
        }
    }

    private void handlePasswordReset() {
        String username = view.getUsernameField().getText();
        String answer = view.getAnswerField().getText();
        String newPassword = view.getNewPasswordField().getText();

        if (username.isEmpty() || answer.isEmpty() || newPassword.isEmpty()) {
            view.getErrorLabel().setText("All fields are required");
            return;
        }

        if (newPassword.length() < 4) {
            view.getErrorLabel().setText("Password must be at least 4 characters");
            return;
        }

        if (userManager.resetPassword(username, answer, newPassword)) {
            onPasswordResetSuccess();
        } else {
            view.getErrorLabel().setText("Incorrect answer or error occurred");
        }
    }

    private void onPasswordResetSuccess() {
        view.getErrorLabel().setText("Password reset successfully!");
        view.getErrorLabel().setColor(0, 1, 0, 1); // Green for success

        // Return to login after delay
        Gdx.app.postRunnable(() -> {
            try {
                Thread.sleep(2000);
                returnToLoginScreen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void returnToLoginScreen() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginView(new LoginController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
