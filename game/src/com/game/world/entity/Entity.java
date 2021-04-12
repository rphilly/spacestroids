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

    public Entity(Vector2f position, Vector2f velocity, double radius, Game instance) {
        this.position = position;
        this.velocity = velocity;
        size = radius;
        game = instance;

        game.entityList.add(this);
    }

    public abstract void input(Mouse mouse);

    public abstract void update();

    public abstract void render(Graphics2D g);

    void remove() {
        game.entityList.remove(this);
    }

    //Every object can detect collision with another object - circle based collision detection
    boolean collisionCheck(Entity otherEntity) {
        double deltaX = position.x - otherEntity.position.x;
        double deltaY = position.y - otherEntity.position.y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY); //Py theorem

        return distance < size + otherEntity.size;
    }
}
