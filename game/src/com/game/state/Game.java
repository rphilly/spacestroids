package com.game.state;

import com.game.util.Sprite;
import com.game.world.entity.Asteroid;
import com.game.world.entity.Bullet;
import com.game.world.entity.Entity;
import com.game.world.entity.Player;
import com.game.util.Mouse;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends State {

    Player player;

    public ArrayList<Entity> entityList;
    public ArrayList<Asteroid> asteroidList;
    public ArrayList<Bullet> bulletList;

    public boolean attack;

    public Game(Panel panel) {
        super(panel);
        setupEntities();
        checkCollision();

        player = new Player(new Vector2f((float) panel.getWidth() / 2, (float) panel.getHeight() / 2), this);

        asteroidList.add(new Asteroid(new Vector2f(-10, -60), this));
    }

    void setupEntities() {
        entityList = new ArrayList<>();
        asteroidList = new ArrayList<>();
        bulletList = new ArrayList<>();
    }

    void checkCollision() {
        for (Asteroid asteroid : asteroidList) {
            asteroid.checkBulletCollision();
        }
    }

    @Override
    public void input(Mouse mouse) {
        player.input(mouse);

        attack = mouse.getButton() == 1;
        if (attack) {
            player.shoot();
        }
    }

    @Override
    public void update() {
        player.update();

        for (Entity asteroid : asteroidList) {
            asteroid.update();
        }

        for (Entity bullet : bulletList) {
            bullet.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        player.render(g);

        for (Entity asteroid : asteroidList) {
            asteroid.render(g);
        }

        for (Entity bullet : bulletList) {
            bullet.render(g);
        }
    }
}
