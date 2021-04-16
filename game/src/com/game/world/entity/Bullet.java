package com.game.world.entity;

import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends Entity {

    public Bullet(Vector2f position, Vector2f size, double rotation, Game instance) {
        super(position, new Vector2f(0, 0), size, rotation - 1.49, "entity/player/bullet_dark_blue.png", instance);

        velocity.x = (float) 7;
        velocity.y = (float) 7;
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
        super.render(g2d);
        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(position.x + (float) sprite.getSprite().getWidth() / 2, position.y + (float) sprite.getSprite().getHeight() / 2);
        g2d.rotate(rotation);
        g2d.translate(-position.x, -position.y);
        g2d.drawImage(sprite.getSprite(), (int) position.x - (int) size.x / 2, (int) position.y - (int) size.y / 2, null);
        g2d.setTransform(oldAT);
    }

    @Override
    void remove() {
        super.remove();
        game.bulletList.remove(this);
    }
}
