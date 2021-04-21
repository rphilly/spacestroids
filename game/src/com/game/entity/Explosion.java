package com.game.entity;

import com.game.state.Game;
import com.game.util.MouseHandler;
import com.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Explosion extends Entity implements Runnable {

    final int EXPLOSION_WIDTH = 128;
    final int EXPLOSION_HEIGHT = 128;
    final int EXPLOSION_STEPS = 15;
    boolean exploding, destroyed = false;
    int explosionStep = 0;

    public static ArrayList<Explosion> tempList = new ArrayList<>();

    public Explosion(Vector2f position, Game instance) {
        super(position, Vector2f.zero(), Vector2f.zero(), 0, "entity/enemy/explosion.png", instance);

        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < EXPLOSION_STEPS; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            explosionStep++;
        }

        tempList.add(this);
    }

    @Override
    public void input(MouseHandler mouse) { }

    @Override
    public void update() { }

    @Override
    public void render(Graphics2D g2d) {
        BufferedImage explosion = sprite.getSprite().getSubimage((explosionStep % 4) * 128, (explosionStep / 4) * 128, 128, 128);
        g2d.drawImage(explosion,
                (int) (position.x - explosion.getWidth() / 2),
                (int) (position.y - explosion.getHeight() / 2),
                EXPLOSION_WIDTH,
                EXPLOSION_HEIGHT,
                null);
    }
}
