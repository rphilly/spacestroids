package com.game.world.entity;

import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    protected Vector2f position, velocity, size;
    protected double rotation;
    protected Game game;

    Rectangle bounds;
    boolean isDrawingBounds;

    public Entity(Vector2f position, Vector2f velocity, Vector2f size, double rotation, Game instance) {
        this.position = position;
        this.velocity = velocity;
        this.size = size;
        this.rotation = rotation;
        game = instance;

        isDrawingBounds = true;
        bounds = new Rectangle((int) position.x, (int) position.y, (int) size.x, (int) size.y);

        game.entityList.add(this);
    }

    public abstract void input(Mouse mouse);

    public abstract void update();

    public void render(Graphics2D g2d) {
        drawBounds(g2d);
    }

    void drawBounds(Graphics2D g2d) {
        if (isDrawingBounds) {
            bounds = this.getOutline();
            g2d.setColor(Color.RED);
            g2d.drawRect((int) position.x, (int) position.y, (int) bounds.getWidth(), (int) bounds.getHeight());
        }
    }

    boolean collisionDetection(Entity entity) {
        double deltaX = position.x - entity.position.x; //Distance in between objects
        double deltaY = position.y - entity.position.y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return distance < (size.x + size.y) + (entity.size.x + entity.size.y);
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

    public Rectangle getOutline() {
        return bounds;
    }
}
