package com.game.world.entity;

import com.game.state.Game;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Asteroid extends Entity {

    private final BufferedImage asteroid;

    private static final Random random = new Random();

    public Asteroid(Vector2f position, Game game) {
        super(position, new Vector2f(0, 0), 0, game);

        int x = random.nextInt(4);
        Sprite asteroidSheet = new Sprite("entity/enemy/sprite_sheet.png");
        asteroid = asteroidSheet.getSprite(3, 0);

        velocity.x = 1;
        velocity.y = 1;

        game.asteroidList.add(this);
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
        g2d.drawImage(asteroid, (int) position.x, (int) position.y, 48, 48, null);
    }
}
