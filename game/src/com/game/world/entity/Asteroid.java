package com.game.world.entity;

import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Asteroid extends Entity {

    private final BufferedImage asteroid;

    public Asteroid(Vector2f position, Vector2f size, double rotation, Game instance) {
        super(position, new Vector2f(0, 0), size, rotation, instance);

        Sprite asteroidSheet = new Sprite("entity/enemy/sprite_sheet.png");
        asteroid = asteroidSheet.getSprite(3, 0);

        velocity.x = 1;
        velocity.y = 1;

        game.asteroidList.add(this);

        System.out.println(getSize());
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
        g2d.drawImage(asteroid, (int) position.x, (int) position.y, (int) size.x, (int) size.y, null);
    }

    void remove() {
        super.remove();
        game.asteroidList.remove(this);
    }
}
