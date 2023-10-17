package com.example.dungeongame.model;

import java.util.ArrayList;
import java.util.Date;

public class Leaderboard {
    private LeaderboardEntry lastEntry;
    private ArrayList<LeaderboardEntry> entries;
    private int size;
    private final int maxSize = 5;
    private static Leaderboard singleton;
    private Leaderboard() {
        lastEntry = new LeaderboardEntry("null", -1, new Date());
        entries = new ArrayList<>();
        size = 0;
    }
    public void addEntry(String name, int score, Date date) {
        LeaderboardEntry newEntry = new LeaderboardEntry(name, score, date);
        lastEntry = newEntry;
        entries.add(newEntry);
        entries.sort(null);
        if (entries.size() > maxSize) {
            entries.remove(maxSize);
        }
    }
    public LeaderboardEntry getLastEntry() {
        return lastEntry;
    }
    public ArrayList<LeaderboardEntry> getEntries() {
        return entries;
    }
    public static Leaderboard getLeaderboard() {
        if (singleton == null) {
            singleton = new Leaderboard();
        }
        return singleton;
    }
}
