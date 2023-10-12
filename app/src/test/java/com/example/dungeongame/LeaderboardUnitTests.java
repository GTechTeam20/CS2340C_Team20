package com.example.dungeongame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeongame.model.Leaderboard;
import com.example.dungeongame.model.LeaderboardEntry;
import com.example.dungeongame.viewmodels.ConfigurationViewModel;
import com.example.dungeongame.viewmodels.EndScreenViewModel;

import org.junit.Test;

import java.util.Date;

public class LeaderboardUnitTests {
    @Test
    public void leaderBoard_mostRecent() {
        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        leaderboard.AddEntry("Test1", 76, new Date());
        assertEquals(leaderboard.getLastEntry().getName(), "Test1");
        assertEquals(leaderboard.getLastEntry().getScore(), 76);
    }

    @Test
    public void leaderBoard_correctOrdering() {
        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        leaderboard.AddEntry("Player2", 76, new Date());
        leaderboard.AddEntry("Player5", 25, new Date());
        leaderboard.AddEntry("Player3", 76, new Date());
        leaderboard.AddEntry("Player4", 50, new Date());
        leaderboard.AddEntry("Player1", 500, new Date());

        assertEquals(leaderboard.getEntries().get(0).getName(), "Player1");
        assertEquals(leaderboard.getEntries().get(1).getName(), "Player2");
        assertEquals(leaderboard.getEntries().get(2).getName(), "Player3");
        assertEquals(leaderboard.getEntries().get(3).getName(), "Player4");
        assertEquals(leaderboard.getEntries().get(4).getName(), "Player5");

    }

    @Test
    public void leaderBoard_getScore() {
        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        leaderboard.AddEntry("Player1", 80, new Date());
        assertEquals(leaderboard.getEntries().get(0).getScore(), 80);
    }

    @Test
    public void leaderBoard_compareTo() {
        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        leaderboard.AddEntry("Player1", 80, new Date());
        leaderboard.AddEntry("Player2", 70, new Date());
        assertEquals(10, leaderboard.getEntries().get(1).compareTo(leaderboard.getEntries().get(0)));
    }

    @Test
    public void leaderboardValid() {
        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        leaderboard.AddEntry("Player1", 50, new Date());
        assertNotNull(leaderboard);
    }

    @Test
    public void leaderBoard_getName() {
        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        leaderboard.AddEntry("Player1", 50, new Date());
        assertEquals(leaderboard.getEntries().get(0).getName(), "Player1");
    }


}
