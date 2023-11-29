package com.example.dungeongame;

import static org.junit.Assert.assertEquals;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.Sword;
import com.example.dungeongame.model.collisions.CollisionManager;
import com.example.dungeongame.model.enemy.Crab;
import com.example.dungeongame.model.enemy.Ghost;
import com.example.dungeongame.model.powerups.Coin;
import com.example.dungeongame.model.powerups.Heart;
import com.example.dungeongame.model.powerups.Speed;

import org.junit.Before;
import org.junit.Test;

public class Sprint5UnitTests {
    private Player player;
    Heart heart = Heart.getInstance(200, 300, "heart");
    Coin coin = Coin.getInstance(200, 300, "coin");
    Speed speed = Speed.getInstance(200, 300, "speed");

    Sword sword =


    @Test
    public void CheckCrabLayer() {
        assertEquals(1, coin.getLayer());
    }

    @Test
    public void CheckGhostLayer() {
        assertEquals(1, speed.getLayer());
    }


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

    @Test
    public void testCoinLayer() {
        assertEquals(coin.getLayer(), 1);
    }

    @Test
    public void testHeartLayer() {
        assertEquals(heart.getLayer(), 1);
    }
    
    @Test
    public void testScore() {
        int score = player.getPlayerScore();
        CollisionManager.getInstance().addCollision(((Coin) coin).getCollisionBox());
        assertEquals(false, player.getPlayerScore() > score);
    }
}
