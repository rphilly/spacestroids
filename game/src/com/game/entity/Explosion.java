package com.game.entity;

import com.game.engine.math.Vector2f;
import com.game.state.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Builds properties of Explosions; uses own-thread to increment explosion steps.
 */
public class Explosion extends Entity implements Runnable {

    /**
     * Variables to store explosion information.
     */
    protected final int EXPLOSION_SIZE = 128;
    protected final int EXPLOSION_STEPS = 15;
    private int explosionStep = 0;

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
                System.out.println("Error: explosion failed..." + e);
            }

            explosionStep++;
        }

        tempList.add(this);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics2D g2d) {
        BufferedImage explosion =
                sprite.getSprite().getSubimage((explosionStep % 4) * EXPLOSION_SIZE, (explosionStep / 4) * EXPLOSION_SIZE, 128, 128);
        g2d.drawImage(explosion,
                (int) (position.x - explosion.getWidth() / 2),
                (int) (position.y - explosion.getHeight() / 2),
                EXPLOSION_SIZE,
                EXPLOSION_SIZE,
                null);
    }
}
