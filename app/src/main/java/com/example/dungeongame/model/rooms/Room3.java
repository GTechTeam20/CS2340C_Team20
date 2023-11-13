package com.example.dungeongame.model.rooms;

import android.graphics.BitmapFactory;

import com.example.dungeongame.R;
import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionType;
import com.example.dungeongame.views.GameActivity;

public class Room3 extends DungeonRoom {
    public Room3() {
        this.background = BitmapFactory.decodeResource(GameActivity.getResourcesRef(),
                R.drawable.room3);
        adjustDensity(this.background, 1000);
        Player.getInstance().updatePosition(500, 1000);
        this.addWall(new CollisionBox(0, 0, 50, 2000, CollisionType.WALL));
        this.addWall(new CollisionBox(950, 0, 50, 2000, CollisionType.WALL));
        this.addWall(new CollisionBox(0, 0, 450, 50, CollisionType.WALL));
        this.addWall(new CollisionBox(450, 0, 100, 50, CollisionType.DOOR));
        this.addWall(new CollisionBox(550, 0, 450, 50, CollisionType.WALL));
        this.addWall(new CollisionBox(0, 1950, 450, 50, CollisionType.WALL));
        this.addWall(new CollisionBox(450, 1950, 100, 50, CollisionType.DOOR));
        this.addWall(new CollisionBox(550, 1950, 450, 50, CollisionType.WALL));
    }

}
