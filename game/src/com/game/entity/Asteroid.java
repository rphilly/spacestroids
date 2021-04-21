package com.game.entity;

import com.game.state.Game;
import com.game.util.*;

import java.util.ArrayList;

public class Asteroid extends Entity {

    public static ArrayList<Asteroid> tempList = new ArrayList<>();
    public static int killcount;

    public Asteroid(Vector2f position, Vector2f velocity, Vector2f size, double rotation, Game instance) {
        super(position, velocity, size, rotation, "entity/enemy/asteroid.png", instance);
        game.asteroidList.add(this);
    }

    @Override
    public void input(MouseHandler mouse) { }

    @Override
    public void update() {
        super.update();
        position.x += velocity.x;
        position.y += velocity.y;

        this.rotation += 0.01;

        SaveScore score = new SaveScore();
        score.write(Integer.toString(killcount));
    }

    public void checkBulletCollision() {
        for (int i = 0; i < game.bulletList.size(); i++) {
            boolean collisionDetected = collisionDetection(game.bulletList.get(i));

            if (collisionDetected) {
                tempList.add(this);
                game.bulletList.get(i).remove();
                new Explosion(position, getGame());
            }
        }
    }

    @Override
    public void remove() {
        super.remove();
        game.asteroidList.remove(this);
        killcount++;
    }
}
