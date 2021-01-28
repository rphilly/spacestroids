package com.game.world.entity;

import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;

import javax.imageio.ImageIO;

import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    public final BufferedImage player;
    //public Point position = new Point(450, 450);
    public Point pointer;
    public double imageAngleRad = 0;

    public Player(Sprite sprite, Vector2f position) {
        super(sprite, position);

        BufferedImage i = null;
        try {
            i = ImageIO.read(new File("res/entity/player/player4.png"));
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        player = i;

        Timer timer = new Timer(40, e -> {
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

    public void render(Graphics g) {
        //g2d.drawImage(player, (int) x, (int) y, player.getWidth() / 2, player.getHeight() / 2,null);
        //g2d.dispose();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        int cx = player.getWidth() / 2;
        int cy = player.getHeight() / 2;
        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(cx + position.x, cy + position.y);
        g2d.rotate(imageAngleRad);
        g2d.translate(-cx, -cy);
        //g2d.drawImage(sprite.getSpriteSheet(), 0, 0, null);
        g2d.drawImage(player, 0, 0, null);
        g2d.setTransform(oldAT);
    }

    public void input(Mouse e) {
        pointer = e.getPointer();
        double dx = e.getX() - position.x;
        double dy = e.getY() - position.y;
        imageAngleRad = Math.atan2(dy, dx);
        //repaint();
    }
}
