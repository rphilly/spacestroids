package com.game.entity;

import com.game.entity.player.Sprite;
import com.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity {

    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }

    public void update() {
        //super.update();
    }

    @Override
    public void render(Graphics g) {
    }
}
