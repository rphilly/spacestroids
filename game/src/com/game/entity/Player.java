package com.game.entity;

import com.game.engine.math.Vector2f;
import com.game.state.Game;
import com.game.util.MouseHandler;

import java.awt.*;

/**
 * Builds properties of Player; handles collision with Asteroids.
 */
public class Player extends Entity {

    /**
     * Variables for player information; accessed within residing class only.
     */
    private Point pointer;
    private boolean isAlive = true;
    private int health = 100;
    private double velocity = 1.5;
    private int triggerCd = 0;
    protected int triggerDelay = 15;

    public Player(Vector2f position, Vector2f size, double rotation, Game instance) {
        super(position, Vector2f.zero(), size, rotation, "entity/player/playertest2.png", instance);
    }

    public void shoot() {
        if (triggerCd == 0) {
            if (isAlive) {
                game.bulletList.add(new Bullet(new Vector2f(position.x, position.y), rotation, game));
                game.attack = false;
                triggerCd = triggerDelay;
            }
        }
    }

    public void checkAsteroidCollision() {
        for (int i = 0; i < game.asteroidList.size(); i++) {
            boolean collisionDetected = collisionDetection(game.asteroidList.get(i));

            if (collisionDetected) {
                health = 0;
                velocity = 0;
                rotation = 0;
                isAlive = false;
                game.asteroidList.get(i).remove();
                game.entityList.remove(this);
                new Explosion(position, getGame());
                break;
            }
        }
    }

    public void input(MouseHandler e) {
        pointer = e.getPointer();
        double x = e.getX() - position.x;
        double y = e.getY() - position.y;
        rotation = Math.atan2(y, x) + Math.PI / 2;
    }

    @Override
    public void update() {
        if (triggerCd > 0) triggerCd--;

        if (health < 1) game.setupScore();

        if (pointer != null) {
            int centerX = (int) position.x + (sprite.getSprite().getWidth() / 2);
            int centerY = (int) position.y + (sprite.getSprite().getHeight() / 2);

            if (pointer.x != centerX) {
                position.x += pointer.x < centerX ? -velocity : velocity;
            }
            if (pointer.y != centerY) {
                position.y += pointer.y < centerY ? -velocity : velocity;
            }
        }
    }

    public boolean isAlive() {
        return isAlive;
    }
}
