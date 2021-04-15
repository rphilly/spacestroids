package com.game.world.entity;

import com.game.engine.Launcher;
import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Vector2f;

import java.awt.*;

public abstract class Entity {

    public static final int WIDTH = Launcher.launcher.getFrame().getWidth();
    public static final int HEIGHT = Launcher.launcher.getFrame().getHeight();

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

        isDrawingBounds = false;

        game.entityList.add(this);
    }

    void drawBounds(Graphics2D g2d) {
        if (isDrawingBounds) {
            bounds = this.getBounds();
            g2d.setColor(Color.RED);
            //g2d.drawRect((int) position.x - (int) bounds.getWidth() / 2, (int) position.y - (int) bounds.getHeight() / 2,
            //(int) bounds.getWidth(), (int) bounds.getHeight());
            g2d.drawOval((int) position.x - ((int) size.x + (int) size.y) / 4, (int) position.y - ((int) size.x + (int) size.y) / 4,
                    ((int) size.x + (int) size.y) / 2, ((int) size.x + (int) size.y) / 2);
        }
    }

    boolean collisionDetection(Entity entity) {
        double deltaX = position.x - entity.position.x; //Distance in between objects
        double deltaY = position.y - entity.position.y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return distance < (size.x + size.y) / 2 + (entity.size.x + entity.size.y) / 2;
    }

    void setupWrap() {
        if (position.x < -size.x) {
            position.x = WIDTH + size.x;

        } else if (position.x > WIDTH + size.x) {
            position.x = -size.x;
        }

        if (position.y < -size.y) {
            position.y = HEIGHT + size.y;

        } else if (position.y > HEIGHT + size.y) {
            position.y = -size.y;
        }
    }

    public abstract void input(Mouse mouse);

    public void update() {
        setupWrap();
    }

    public void render(Graphics2D g2d) {
        drawBounds(g2d);
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

    public Rectangle getBounds() {
        return new Rectangle((int) position.x, (int) position.y, (int) size.x, (int) size.y);
    }
}
