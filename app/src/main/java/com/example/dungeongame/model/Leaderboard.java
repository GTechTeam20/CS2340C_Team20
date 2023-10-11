package com.example.dungeongame.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Leaderboard {
    private LeaderboardEntry lastEntry;
    private ArrayList<LeaderboardEntry> entries;
    private int size;
    private int MAX_SIZE = 5;
    private static Leaderboard singleton;
    public Leaderboard() {
        lastEntry = new LeaderboardEntry("null", -1, new Date());
        entries = new ArrayList<>();
        size = 0;
        //TODO: Remove this (its just for testing purposes)
        for (int i = 1; i < 6; i += 1) {
            AddEntry("Player" + i, Math.abs((i - 3) * (4 - i)) * 10, new Date());
        }
    }
    public void AddEntry(String name, int score, Date date) {
        LeaderboardEntry newEntry = new LeaderboardEntry(name, score, date);
        lastEntry = newEntry;
        entries.add(newEntry);
        entries.sort(null);
        if (entries.size() > MAX_SIZE) {
            entries.remove(MAX_SIZE);
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
