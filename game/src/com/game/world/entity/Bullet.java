package com.game.world.entity;

import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet extends Entity {

    //private final Sprite b = new Sprite("entity/player/bullet.png");
    private final BufferedImage bullet;
    private final double rotation;

    public Bullet(Vector2f position, double rotation, Game instance) {
        super(position, new Vector2f(0, 0), 0, instance);
        this.rotation = rotation - 1.49;

        BufferedImage i = null;
        try {
            i = ImageIO.read(new File("res/entity/player/bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bullet = i;

        velocity.x = (float) 7;
        velocity.y = (float) 7;

        game.bulletList.add(this);
    }

    @Override
    public void input(Mouse e) {

    }

    @Override
    public void update() {
        position.x += velocity.x * Math.cos(rotation);
        position.y += velocity.y * Math.sin(rotation);
    }

    @Override
    public void render(Graphics2D g2d) {
        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(position.x + (float) bullet.getWidth() / 2, position.y + (float) bullet.getHeight() / 2);
        g2d.rotate(rotation);
        g2d.translate(-position.x, -position.y);
        g2d.drawImage(bullet, (int) position.x, (int) position.y, null);
        g2d.setTransform(oldAT);
    }
}
