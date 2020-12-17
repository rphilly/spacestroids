package com.game.entity;

import com.game.entity.player.Sprite;
import com.game.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    public Entity(Sprite sprite, Vector2f pos, int size) {
        this.sprite = sprite;
        this.pos = pos;
        this.size = size;
    }

    public abstract void update();

    public abstract void render(Graphics g);
}
