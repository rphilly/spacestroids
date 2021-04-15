package com.game.world.entity;

import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Asteroid extends Entity {

    private final BufferedImage asteroid;

    public static ArrayList<Asteroid> tempList = new ArrayList<>();

    public Asteroid(Vector2f position, Vector2f velocity, Vector2f size, double rotation, Game instance) {
        super(position, velocity, size, rotation, instance);

        Sprite asteroidSheet = new Sprite("entity/enemy/sprite_sheet.png");
        asteroid = asteroidSheet.getSprite(3, 0);

        game.asteroidList.add(this);
    }

    public void checkBulletCollision() {
        for (int i = 0; i < game.bulletList.size(); i++) {
            boolean collisionDetected = collisionDetection(game.bulletList.get(i));

            if (collisionDetected) {
                tempList.add(this);
                game.bulletList.get(i).remove();
            }
        }
    }

    @Override
    public void input(Mouse mouse) {

    }

    @Override
    public void update() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.drawImage(asteroid, (int) position.x - (int) size.x / 2, (int) position.y - (int) size.y / 2, (int) size.x, (int) size.y, null);
    }

    @Override
    void remove() {
        super.remove();
        game.asteroidList.remove(this);
    }
}
