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

    public Bullet(Entity shooter, double rotation, Game game) {
        super(new Vector2f(shooter.position.x, shooter.position.y), new Vector2f(0, 0), 0, game);
        this.rotation = rotation;

        BufferedImage i = null;
        try {
            i = ImageIO.read(new File("res/entity/player/bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bullet = i;

        velocity.x = (float) 0.5;
        velocity.y = (float) 0.5;

        game.bulletList.add(this);
    }

    @Override
    public void input(Mouse e) {

    }

    @Override
    public void update() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    @Override
    public void render(Graphics2D g2d) {
        AffineTransform oldAT = g2d.getTransform();
        g2d.rotate(rotation);
        //System.out.println(rotation);
        g2d.drawImage(bullet, (int) position.x, (int) position.y, null);
        //g2d.setColor(Color.GREEN);
        //g2d.drawRect((int) position.x, (int) position.y, 15, 15);
        //g2d.drawRoundRect((int) position.x, (int) position.y, 25, 25, 25, 25);
        //System.out.println("x: " + position.x + ", y: " + position.y);
        g2d.setTransform(oldAT);
    }
}
