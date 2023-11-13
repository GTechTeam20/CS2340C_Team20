package com.example.dungeongame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.viewmodels.GameViewModel;
import com.example.dungeongame.views.GameActivity;

public class GameActivityUnitTests {
    private GameActivity gameActivity;
    private GameViewModel vm;

    @Before
    public void setUp() {
        gameActivity = new GameActivity(); // Instantiate the activity

    }

    @Test
    public void testStartingHealthForEasyDifficulty() {
        // Call the method that sets starting health for "Easy" difficulty
        int startingHealth = gameActivity.getStartingHealthForDifficulty("Easy");

        // Verify that the starting health is set to 100 for "Easy" difficulty
        assertEquals(100, startingHealth);
    }

    @Test
    public void testStartingHealthForMediumDifficulty() {
        // Call the method that sets starting health for "Medium" difficulty
        int startingHealth = gameActivity.getStartingHealthForDifficulty("Medium");

        // Verify that the starting health is set to 75 for "Medium" difficulty
        assertEquals(75, startingHealth);
    }

    @Test
    public void testStartingHealthForHardDifficulty() {
        // Call the method that sets starting health for "Hard" difficulty
        int startingHealth = gameActivity.getStartingHealthForDifficulty("Hard");

        // Verify that the starting health is set to 50 for "Hard" difficulty
        assertEquals(50, startingHealth);
    }

}