package com.example.dungeongame;

import static org.junit.Assert.assertEquals;

import androidx.constraintlayout.utils.widget.MockView;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionManager;
import com.example.dungeongame.model.enemy.Crab;
import com.example.dungeongame.model.enemy.Enemy;
import com.example.dungeongame.model.enemy.Ghost;
import com.example.dungeongame.model.enemy.Goblin;
import com.example.dungeongame.model.enemy.Zombie;
import com.example.dungeongame.model.rooms.DungeonRoom;
import com.example.dungeongame.model.rooms.Room1;
import com.example.dungeongame.model.rooms.Room2;
import com.example.dungeongame.model.rooms.Room3;
import com.example.dungeongame.viewmodels.GameViewModel;
import com.example.dungeongame.views.GameActivity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class Sprint4UnitTests {

    //private GameActivity gameActivity;
    private GameViewModel vm;

    private Player player;

    Crab crab = Crab.getInstance(200, 300, "crab", 100, 700, 100, 500);
    Ghost ghost = Ghost.getInstance(200, 300, "ghost", 100, 700, 100, 500);

    Goblin goblin = Goblin.getInstance(200, 300, "goblin", 100, 700, 100, 500);

    Zombie zombie = Zombie.getInstance(200, 300, "zombie", 100, 700, 100, 500);

    @Before
    public void setUp() {
        //gameActivity = new GameActivity();
        //vm =  new GameViewModel(new ArrayList<>());
        player = Player.getInstance();
    }
    @Test
    public void testNonEnemyCollision() {
        int health = player.getPlayerHealth();
        CollisionManager.getInstance().addCollision(((Enemy) ghost).getCollisionBox());
        assertEquals(false, player.attemptMove(2, 2, 1));
        assertEquals(false, player.getPlayerHealth() < health);

    }
    @Test
    public void testEnemyCollision() {
        int health = player.getPlayerHealth();
        CollisionManager.getInstance().addCollision(((Enemy) ghost).getCollisionBox());
        assertEquals(false, player.attemptMove(200, 300, 1));
        assertEquals(false, player.getPlayerHealth() < health);
    }
    @Test
    public void CheckCrabLayer() {
        assertEquals(1, crab.getLayer());
    }

    @Test
    public void CheckGhostLayer() {
        assertEquals(1, ghost.getLayer());
    }
    @Test
    public void CheckZombieLayer() {
        assertEquals(1, zombie.getLayer());
    }

    @Test
    public void CheckGoblinLayer() {
        assertEquals(1, goblin.getLayer());
    }
}
