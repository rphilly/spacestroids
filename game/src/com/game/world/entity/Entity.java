package com.game.world.entity;

import com.game.state.Game;
import com.game.util.AABB;
import com.game.util.Mouse;
import com.game.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    protected Vector2f position, velocity, size;
    protected double rotation;
    protected Game game;

    AABB bounds;
    boolean isDrawingBounds = true;

    public Entity(Vector2f position, Vector2f velocity, Vector2f size, double rotation, Game instance) {
        this.position = position;
        this.velocity = velocity;
        this.size = size;
        this.rotation = rotation;
        game = instance;

        bounds = new AABB(position, (int) size.x, (int) size.y);

        game.entityList.add(this);
    }

    public abstract void input(Mouse mouse);

    public abstract void update();

    public void render(Graphics2D g2d) {
        drawBounds(g2d);
    }

    void drawBounds(Graphics2D g2d) {
        if (isDrawingBounds) {
            AABB bounds = this.getBounds();
            g2d.setColor(Color.RED);
            g2d.drawRect((int) bounds.position.x, (int) bounds.position.y, (int) bounds.getWidth(), (int) bounds.getHeight());
        }
    }

    void remove() {
        game.entityList.remove(this);
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public Vector2f getSize() {
        return size;
    }

    public AABB getBounds() {
        return bounds;
    }
}
