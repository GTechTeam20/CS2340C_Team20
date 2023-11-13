package com.example.dungeongame;

import com.example.dungeongame.model.enemy.Crab;
import com.example.dungeongame.model.enemy.Ghost;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EnemyUnitTests {
    Crab crab = Crab.getInstance(200, 300, "crab", 100, 700, 100, 500);
    Ghost ghost = Ghost.getInstance(200, 300, "ghost", 100, 700, 100, 500);


    @Test
    public void CheckCrabLayer() {
        assertEquals(1, crab.getLayer());
    }

    @Test
    public void CheckGhostLayer() {
        assertEquals(1, ghost.getLayer());
    }
}
