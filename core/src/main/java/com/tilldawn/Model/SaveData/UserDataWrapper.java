package com.tilldawn.Model.SaveData;

import java.io.Serializable;
import java.util.List;

public class UserDataWrapper implements Serializable {
    private List<User> users;
    private String currentUsername;

    public UserDataWrapper(List<User> users, String currentUsername) {
        this.users = users;
        this.currentUsername = currentUsername;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}
