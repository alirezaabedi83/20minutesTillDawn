package com.tilldawn.Model.SaveData;

import com.tilldawn.Main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private User currentUser;

    public UserManager() {
        loadUsers();
    }

    private void loadUsers() {
        try {
            Object loaded = ResourceManager.load();
            if (loaded instanceof List) {
                users = (List<User>) loaded;
            } else {
                users = new ArrayList<>();
            }
        } catch (Exception e) {
            users = new ArrayList<>();
        }
    }

    private void saveUsers() {
        try {
            ResourceManager.save(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean register(String username, String password, String securityQuestion, String securityAnswer) {
        if (usernameExists(username)) {
            return false;
        }

        User newUser = new User(username, password, securityQuestion, securityAnswer);
        users.add(newUser);
        saveUsers();
        return true;
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.currentUser = user;
                Main.currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
        Main.currentUser = null;
    }

    public String getSecurityQuestion(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getSecurityQuestion();
            }
        }
        return null;
    }

    public boolean resetPassword(String username, String answer, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                user.getSecurityAnswer().equalsIgnoreCase(answer)) {
                user.setPassword(newPassword);
                saveUsers();

                // Update current user if logged in
                if (currentUser != null && currentUser.getUsername().equals(username)) {
                    currentUser.setPassword(newPassword);
                }
                return true;
            }
        }
        return false;
    }

    public boolean usernameExists(String username) {
        return users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
    }

    public void saveCurrentUser() {
        if (currentUser != null) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(currentUser.getUsername())) {
                    users.set(i, currentUser);
                    break;
                }
            }
            saveUsers();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
