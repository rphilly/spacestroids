package com.game.world.entity;

import com.game.engine.view.Panel;
import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Vector2f;

import javax.imageio.ImageIO;

import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    private final BufferedImage player;
    private Point pointer;
    private double imageAngleRad = 0;

    private boolean attack, keyLock;

    Panel panel;

    public Player(Game game) {
        super(new Vector2f(0, 0), new Vector2f(0, 0), 0, game);
        position.x = 50;
        position.y = 50;

        BufferedImage i = null;
        try {
            i = ImageIO.read(new File("res/entity/player/player4.png"));
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        player = i;

        //Continuously evaluate current mouse & image position
        Timer timer = new Timer(25, e -> {
            if (pointer != null) {

                int centerX = (int) position.x + (player.getWidth() / 2);
                int centerY = (int) position.y + (player.getHeight() / 2);

                if (pointer.x != centerX) {
                    position.x += pointer.x < centerX ? -1 : 1;
                }
                if (pointer.y != centerY) {
                    position.y += pointer.y < centerY ? -1 : 1;
                }
                //repaint();
            }
        });
        timer.start();
    }

    @Override
    public void input(Mouse e) {
        pointer = e.getPointer();
        double dx = e.getX() - position.x;
        double dy = e.getY() - position.y;
        imageAngleRad = Math.atan2(dy, dx) + 1.49; //90
        //repaint();

        if (e.getButton() == -1) {
            keyLock = true;
        }

        attack = e.getButton() == 1;
    }

    @Override
    public void update() {
        if (attack) {
            if (keyLock) {
                game.addObj(new Bullet(this, imageAngleRad, game));
                keyLock = false;
            }
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        int cx = player.getWidth() / 2;
        int cy = player.getHeight() / 2;
        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(cx + position.x, cy + position.y);
        g2d.rotate(imageAngleRad);
        g2d.translate(-cx, -cy);
        g2d.drawImage(player, 0, 0, null);
        g2d.setTransform(oldAT);
    }
}
