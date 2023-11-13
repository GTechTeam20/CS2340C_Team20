package com.example.dungeongame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.collisions.CollisionManager;
import com.example.dungeongame.model.enemy.Enemy;
import com.example.dungeongame.model.enemy.Ghost;

import java.util.ArrayList;
import java.util.List;

public class PlayerUnitTest {
    Player player = Player.getInstance();
    Ghost ghostEnemy = Ghost.getInstance(200, 300, "ghost", 100, 700, 100, 500);


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

    @Test
    public void testNonEnemyCollision() {
        int health = player.getPlayerHealth();
        CollisionManager.getInstance().addCollision(((Enemy) ghostEnemy).getCollisionBox());
        assertEquals(false, player.attemptMove(2, 2, 1));
        assertEquals(false, player.getPlayerHealth() < health);

    }
    @Test
    public void testEnemyCollision() {
        int health = player.getPlayerHealth();
        CollisionManager.getInstance().addCollision(((Enemy) ghostEnemy).getCollisionBox());
        assertEquals(false, player.attemptMove(200, 300, 1));
        assertEquals(false, player.getPlayerHealth() < health);
    }
}
