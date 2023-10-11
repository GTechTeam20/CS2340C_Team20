package com.example.dungeongame.viewmodels;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.dungeongame.model.Leaderboard;
import com.example.dungeongame.model.LeaderboardEntry;

import java.util.ArrayList;

public class EndScreenViewModel {
    public ArrayAdapter<String> getLeaderboard(Context parentActivity) {
        ArrayList<String> entries = new ArrayList<>();
        ArrayAdapter<String> leaderboard = new ArrayAdapter<>(parentActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                entries);
        entries.add("Your score: " + Leaderboard.getLeaderboard().getLastEntry().getScore());
        int number = 1;
        for (LeaderboardEntry entry : Leaderboard.getLeaderboard().getEntries()) {
            entries.add(number + ". " + entry.toString());
            number += 1;
        }
        return leaderboard;
    }
}
