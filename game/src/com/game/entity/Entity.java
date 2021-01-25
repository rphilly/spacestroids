package com.game.entity;

import com.game.entity.player.Sprite;
import com.game.util.Vector2f;

public abstract class Entity {

    protected Sprite sprite;
    protected Vector2f position;
    protected int size;

    public Entity(Vector2f position) {
        this.position = position;
    }

    public Entity(Vector2f position, int size) {
        this.position = position;
        this.size = size;
    }

    public Entity(Sprite sprite, Vector2f position, int size) {
        this.sprite = sprite;
        this.position = position;
        this.size = size;
    }
}
