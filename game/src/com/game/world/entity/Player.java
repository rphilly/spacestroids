package com.game.world.entity;

import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Vector2f;

import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Entity {

    private Point pointer;

    int triggerDelay = 15;
    int triggerCooldown = 0;

    public Player(Vector2f position, Vector2f size, double rotation, Game instance) {
        super(position, new Vector2f(0, 0), size, rotation, "entity/player/player1.png", instance);

        //Continuously evaluate current mouse & image position
        Timer timer = new Timer(20, e -> {
            if (pointer != null) {

                int centerX = (int) position.x + (sprite.getSprite().getWidth());
                int centerY = (int) position.y + (sprite.getSprite().getHeight());

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

    public void shoot() {
        if (triggerCooldown == 0) {
            game.bulletList.add(new Bullet(new Vector2f(position.x, position.y), new Vector2f(10, 20), rotation, game));
            game.attack = false;
            triggerCooldown = triggerDelay;
        }
    }

    @Override
    public void input(Mouse e) {
        pointer = e.getPointer();
        double x = e.getX() - position.x;
        double y = e.getY() - position.y;
        rotation = Math.atan2(y, x) + 1.49;
        //repaint();
    }

    @Override
    public void update() {
        super.update();
        if (triggerCooldown > 0) triggerCooldown--;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int centerX = sprite.getSprite().getWidth() / 2;
        int centerY = sprite.getSprite().getHeight() / 2;
        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(centerX + position.x, centerY + position.y);
        g2d.rotate(rotation);
        g2d.translate(-centerX, -centerY);
        g2d.drawImage(sprite.getSprite(), 0, 0, (int) size.x, (int) size.y, null);
        //System.out.println("player W: " + player.getWidth() + ", H: " + player.getHeight()); //39, 62
        g2d.setTransform(oldAT);
    }
}
