package com.tilldawn.Model.SaveData;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private int lastRound;
    private int score;
    private int killCount;
    private int accuracy;

    public User(String username, String password, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.lastRound = 0;
        this.score = 0;
        this.killCount = 0;
        this.accuracy = 0;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getSecurityQuestion() { return securityQuestion; }
    public String getSecurityAnswer() { return securityAnswer; }
    public int getLastRound() { return lastRound; }
    public int getScore() { return score; }
    public int getKillCount() { return killCount; }
    public int getAccuracy() { return accuracy; }

    public void setPassword(String password) { this.password = password; }
    public void setSecurityQuestion(String securityQuestion) { this.securityQuestion = securityQuestion; }
    public void setSecurityAnswer(String securityAnswer) { this.securityAnswer = securityAnswer; }
    public void setLastRound(int lastRound) { this.lastRound = lastRound; }
    public void setScore(int score) { this.score = score; }
    public void setKillCount(int killCount) { this.killCount = killCount; }
    public void setAccuracy(int accuracy) { this.accuracy = accuracy; }
}
