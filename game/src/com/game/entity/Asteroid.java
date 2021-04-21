package com.game.entity;

import com.game.state.Game;
import com.game.util.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Asteroid extends Entity {

    public static ArrayList<Asteroid> tempList = new ArrayList<>();
    public static int killcount;
    SpriteLoader sl;

    static final int EXPLOSION_WIDTH = 128;
    static final int EXPLOSION_HEIGHT = 128;
    static final int EXPLOSION_STEPS = 15;
    boolean exploding, destroyed = false;
    static int explosionStep = 0;

    public Asteroid(Vector2f position, Vector2f velocity, Vector2f size, double rotation, Game instance) {
        super(position, velocity, size, rotation, "entity/enemy/asteroid.png", instance);
        game.asteroidList.add(this);

        sl = new SpriteLoader("entity/enemy/explosion.png");
    }

    public void checkBulletCollision() {
        for (int i = 0; i < game.bulletList.size(); i++) {
            boolean collisionDetected = collisionDetection(game.bulletList.get(i));

            if (collisionDetected) {
                tempList.add(this);
                game.bulletList.get(i).remove();
                explode();
            }
        }
    }

    @Override
    protected boolean collisionDetection(Entity entity) {
        double deltaX = (position.x) - (entity.position.x); //Difference between positions
        double deltaY = (position.y) - (entity.position.y);
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return distance < size.x / 2 + Math.min(entity.size.x, entity.size.y) / 2;
    }

    public void explode() {
        exploding = true;
        explosionStep = -1;
    }

    @Override
    public void input(MouseHandler mouse) { }

    @Override
    public void update() {
        super.update();
        position.x += velocity.x;
        position.y += velocity.y;

        this.rotation += 0.01;

        if (exploding) explosionStep++;
        destroyed = explosionStep > EXPLOSION_STEPS;

        //SaveScore score = new SaveScore();
        //score.write(Integer.toString(killcount));
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (exploding) {
            BufferedImage explosion = sl.getSprite().getSubimage(0, 0, 128, 128);
            g2d.drawImage(explosion,
                    (int) (position.x - sprite.getSprite().getWidth() / 2),
                    (int) (position.y - sprite.getSprite().getHeight() / 2),
                    EXPLOSION_WIDTH,
                    EXPLOSION_HEIGHT,
                    null);
        }
    }

    @Override
    public void remove() {
        super.remove();
        game.asteroidList.remove(this);
        killcount++;
    }
}
