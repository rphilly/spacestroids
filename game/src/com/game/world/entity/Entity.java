package com.game.world.entity;

import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    protected Sprite sprite;
    protected Vector2f position;
    protected int size;
    protected Vector2f velocity = new Vector2f(0, 0);
    //all entities need a velocity?

    public Entity(Vector2f position) {
        this.position = position;
    }

    public Entity(Vector2f position, int size) {
        this.position = position;
        this.size = size;
    }

    public Entity(Sprite sprite, Vector2f position) {
        this.sprite = sprite;
        this.position = position;
    }

    public Entity(Sprite sprite, Vector2f position, int size) {
        this.sprite = sprite;
        this.position = position;
        this.size = size;
    }

    public abstract void input(Mouse mouse);

    public abstract void update();

    public abstract void render(Graphics2D g);
}
