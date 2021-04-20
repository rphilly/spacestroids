package com.game.world.entity;

import com.game.state.Game;
import com.game.util.MouseHandler;
import com.game.util.Vector2f;

import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private Point pointer;

    int triggerDelay = 15;
    int triggerCooldown = 0;

    private int health = 100;

    public Player(Vector2f position, Vector2f size, double rotation, Game instance) {
        super(position, new Vector2f(0, 0), size, rotation, "entity/player/playertest2.png", instance);

        //Continuously evaluate current mouse & image position
        Timer timer = new Timer(20, e -> {

        });

        timer.start();
    }

    public void shoot() {
        if (triggerCooldown == 0) {
            game.bulletList.add(new Bullet(new Vector2f(position.x, position.y), new Vector2f(10, 20), rotation, game));
            game.attack = false;
            triggerCooldown = triggerDelay;
        }
    }

    @Override
    public void input(MouseHandler e) {
        pointer = e.getPointer();
        double x = e.getX() - position.x;
        double y = e.getY() - position.y;
        rotation = Math.atan2(y, x) + Math.PI / 2;
    }

    @Override
    public void update() {
        super.update();
        if (triggerCooldown > 0) triggerCooldown--;

        if (health < 1) {
            game.playerDeath();
        }

        if (pointer != null) {

            int centerX = (int) position.x + (sprite.getSprite().getWidth() / 2);
            int centerY = (int) position.y + (sprite.getSprite().getHeight() / 2);

            if (pointer.x != centerX) {
                position.x += pointer.x < centerX ? -1 : 1;
            }
            if (pointer.y != centerY) {
                position.y += pointer.y < centerY ? -1 : 1;
            }
        }
    }
}
