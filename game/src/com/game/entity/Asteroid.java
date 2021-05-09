package com.game.entity;

import com.game.state.Game;
import com.game.state.State;
import com.game.util.*;

import java.util.ArrayList;

/**
 * Builds properties of Asteroid objects.
 */
public class Asteroid extends Entity {

    public static ArrayList<Asteroid> tempList = new ArrayList<>();
    public static int killcount;

    public Asteroid(Vector2f position, Vector2f velocity, Vector2f size, double rotation, Game instance) {
        super(position, velocity, size, rotation, "entity/enemy/asteroid.png", instance);

        game.asteroidList.add(this);
    }

    public Asteroid(Vector2f position, Vector2f velocity, Vector2f size, double rotation) {
        super(position, velocity, size, rotation, "entity/enemy/asteroid.png", null);
    }

    public void checkBulletCollision() {
        for (int i = 0; i < game.bulletList.size(); i++) {
            boolean collisionDetected = collisionDetection(game.bulletList.get(i));

            if (collisionDetected) {
                tempList.add(this);
                game.bulletList.get(i).remove();
                killcount++;
                new Explosion(position, getGame());
            }
        }
    }

    public void setupWrap(State state) {
        if (position.x < -size.x) {
            position.x = state.panel.getWidth() + size.x;
        } else if (position.x > state.panel.getWidth() + size.x) {
            position.x = -size.x;
        }

        if (position.y < -size.y) {
            position.y = state.panel.getHeight() + size.y;
        } else if (position.y > state.panel.getHeight() + size.y) {
            position.y = -size.y;
        }
    }

    public void move() {
        position.x += velocity.x;
        position.y += velocity.y;

        this.rotation += 0.01;
    }

    @Override
    public void update() {
        move();

        setupWrap(game);
    }

    @Override
    public void remove() {
        super.remove();
        game.asteroidList.remove(this);
    }

    public static void setKillcount(int killcount) { //??
        Asteroid.killcount = killcount;
    }
}
