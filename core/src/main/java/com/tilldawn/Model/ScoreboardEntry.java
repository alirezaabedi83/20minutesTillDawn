package com.tilldawn.Model;

public class ScoreboardEntry {
    private final String username;
    private final int score;
    private final int kills;
    private final float timeAlive;

    public ScoreboardEntry(String username, int score, int kills, float timeAlive) {
        this.username = username;
        this.score = score;
        this.kills = kills;
        this.timeAlive = timeAlive;
    }

    public String getUsername() { return username; }
    public int getScore() { return score; }
    public int getKills() { return kills; }
    public float getTimeAlive() { return timeAlive; }
}
