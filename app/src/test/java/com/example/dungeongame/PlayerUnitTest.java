package com.example.dungeongame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.Sprite;

public class PlayerUnitTest {
    Player player = Player.getInstance();


    @Test
    public void testOutOfMapCheckerIsTrue() {
        assertEquals(true, player.attemptMove(2,2,1));
        assertEquals(true, player.attemptMove(1,30,1));
    }
    @Test
    public void testOutOfMapCheckerIsFalse() {
        assertEquals(false, player.attemptMove(-1,-1,2));
        assertEquals(false, player.attemptMove(-15,-11,3));

    }
    @Test
    public void testStartingPlayerX() {
        player.resetPosition(0);
        int xVal = player.getPlayerX();
        assertEquals(xVal, 100);
    }
    @Test
    public void testStartingPlayerY() {
        player.resetPosition(0);
        int yVal = player.getPlayerY();
        assertEquals(yVal, 700);
    }

    @Test
    public void checkDoor1() {
        player.resetPosition(0);
        assertEquals(player.attemptMove(-600, -100, 0), true);
    }

    @Test
    public void checkDoor2() {
        player.resetPosition(0);
        assertEquals(player.attemptMove(-100, 400, 0), false);
    }

    @Test
    public void checkDoor3() {
        player.resetPosition(0);
        assertEquals(player.attemptMove(-600, -100, 0), true);
    }

    @Test
    public void checkInBounds() {
        assertEquals(player.checkCollisions(0, 500, 0), 1);
    }

    @Test
    public void checkOutBounds1() {
        assertEquals(player.checkCollisions(1000, 0, 0), 0);
    }

    @Test
    public void checkOutBounds2() {
        assertEquals(player.checkCollisions(0, 1000, 0), 0);
    }
    public void testMoveToExitIsSuccessful() {
        // Attempt a move to the exit, expect true
        assertEquals(true, player.attemptMove(0, 100, 0));
    }

    @Test
    public void testMoveToWallIsUnsuccessful() {
        // Attempt a move into a wall, expect false
        assertEquals(false, player.attemptMove(-500, -500, 0));
    }
}
