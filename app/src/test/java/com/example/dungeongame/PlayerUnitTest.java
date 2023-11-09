package com.example.dungeongame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.example.dungeongame.model.Player;

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

    //@Test
    //public void checkInBounds() {
    //    assertEquals(player.checkCollisions(0, 500, 0), 1);
    //}

    //@Test
    //public void checkOutBounds1() {
    //    assertEquals(player.checkCollisions(1000, 0, 0), 0);
    //}

    //@Test
    //public void checkOutBounds2() {
    //    assertEquals(player.checkCollisions(0, 1000, 0), 0);
    //}
    public void testMoveToExitIsSuccessful() {
        // Attempt a move to the exit, expect true
        assertEquals(true, player.attemptMove(0, 100, 0));
    }

    @Test
    public void testMoveToWallIsUnsuccessful() {
        // Attempt a move into a wall, expect false
        assertEquals(false, player.attemptMove(-500, -500, 0));
    }
    @Test
    public void testMoveUpandDown() {
        assertEquals(true, player.attemptMove(2,2,1));
        assertEquals(true, player.attemptMove(2,3,1));
        assertEquals(true, player.attemptMove(2,1,1));

    }
    @Test
    public void testMoveLeftandRight() {
        assertEquals(true, player.attemptMove(2,2,1));
        assertEquals(true, player.attemptMove(1,2,1));
        assertEquals(true, player.attemptMove(3,2,1));
    }
}
