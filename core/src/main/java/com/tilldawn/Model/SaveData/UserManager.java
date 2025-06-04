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
            if (loaded instanceof UserDataWrapper) {
                UserDataWrapper wrapper = (UserDataWrapper) loaded;
                users = wrapper.getUsers() != null ? wrapper.getUsers() : new ArrayList<>();
                String currentUsername = wrapper.getCurrentUsername();

                if (currentUsername != null) {
                    for (User user : users) {
                        if (user.getUsername().equalsIgnoreCase(currentUsername)) {
                            currentUser = user;
                            Main.currentUser = user;
                            break;
                        }
                    }
                }
            } else {
                users = new ArrayList<>();
            }
        } catch (Exception e) {
            users = new ArrayList<>();
        }
    }

    private void saveUsers() {
        try {
            String currentUsername = currentUser != null ? currentUser.getUsername() : null;
            ResourceManager.save(new UserDataWrapper(users, currentUsername));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean register(String username, String password, String securityQuestion, String securityAnswer) {
        if (usernameExists(username)) return false;

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
                saveUsers(); // به‌روزرسانی currentUsername
                return true;
            }
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
        Main.currentUser = null;
        saveUsers();
    }

    public boolean resetPassword(String username, String answer, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getSecurityAnswer().equalsIgnoreCase(answer)) {
                user.setPassword(newPassword);
                saveUsers();
                return true;
            }
        }
        return false;
    }

    public void saveCurrentUser() {
        saveUsers(); // هر تغییری انجام بشه، مستقیماً سیو بشه
    }

    public boolean usernameExists(String username) {
        return users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        saveUsers();
    }

    public String getSecurityQuestion(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getSecurityQuestion();
            }
        }
        return null;
    }

}
