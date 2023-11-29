package com.example.dungeongame;

import static org.junit.Assert.assertEquals;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.collisions.CollisionManager;
import com.example.dungeongame.model.powerups.Heart;

import org.junit.Before;
import org.junit.Test;

public class Sprint5UnitTests {
    private Player player;
    Heart heart = Heart.getInstance(200, 300, "heart");



    @Before
    public void setUp() {
        player = Player.getInstance();
    }
    @Test
    public void testCoinCollision() {
        int health = player.getPlayerHealth();
        CollisionManager.getInstance().addCollision(((Heart) heart).getCollisionBox());
        assertEquals(false, player.attemptMove(200, 300, 1));
        assertEquals(false, player.getPlayerHealth() > health);
    }

    @Test
    public void testNonCoinCollision() {
        int health = player.getPlayerHealth();
        CollisionManager.getInstance().addCollision(((Heart) heart).getCollisionBox());
        assertEquals(false, player.attemptMove(100, 500, 1));
        assertEquals(false, player.getPlayerHealth() > health);
    }
}
