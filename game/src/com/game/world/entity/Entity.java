package com.game.world.entity;

import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    protected Vector2f position, velocity;
    protected double size;
    protected Game game;
    protected Sprite sprite;

    public Entity(Vector2f position, Vector2f velocity, double radius, Game game) {
        this.position = position;
        this.velocity = velocity;
        size = radius;
        this.game = game;

        game.entityList.add(this);
    }

    public abstract void input(Mouse mouse);

    public abstract void update();

    public abstract void render(Graphics2D g);
}
