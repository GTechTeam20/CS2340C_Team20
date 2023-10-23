package com.example.dungeongame;
import org.junit.Before;
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
}
