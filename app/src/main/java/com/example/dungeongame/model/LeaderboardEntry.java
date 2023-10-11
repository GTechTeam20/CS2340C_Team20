package com.example.dungeongame.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
    private String name;
    private int score;
    private Date date;
    public LeaderboardEntry(String name, int score, Date date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    @Override
    public int compareTo(LeaderboardEntry other) {
        if (other.score == this.score) {
            return other.date.compareTo(this.date);
        }
        return other.score - this.score;
    }
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd, HH:mm:ss");
        return this.name + "  " + String.valueOf(this.score) + "  " + sdf.format(date);
    }
}
