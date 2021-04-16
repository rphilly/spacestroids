package com.game.state;

import com.game.world.entity.Asteroid;
import com.game.world.entity.Bullet;
import com.game.world.entity.Entity;
import com.game.world.entity.Player;
import com.game.util.MouseHandler;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends State {

    private final Player player;

    public ArrayList<Entity> entityList;
    public ArrayList<Asteroid> asteroidList;
    public ArrayList<Bullet> bulletList;

    public boolean attack;

    public Game(Panel panel) {
        super(panel);
        setupEntities();
        spawnAsteroids(15);

        player = new Player(new Vector2f((float) Entity.WIDTH / 2, (float) Entity.HEIGHT / 2), new Vector2f(39, 62),0, this);
    }

    void setupEntities() {
        entityList = new ArrayList<>();
        asteroidList = new ArrayList<>();
        bulletList = new ArrayList<>();
    }

    void spawnAsteroids(int amount) {
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            int x = random.nextInt(Entity.WIDTH);
            int y = random.nextInt(Entity.HEIGHT);

            float differenceX = (float) Math.random();
            float differenceY = (float) Math.random();

            Vector2f position = new Vector2f(x, y);
            Vector2f velocity = new Vector2f(differenceX, differenceY);

            new Asteroid(position, velocity, new Vector2f(64, 64), 0,this);
        }
    }

    @Override
    public void input(MouseHandler mouse) {
        player.input(mouse);

        attack = mouse.getButton() == 1;
        if (attack) {
            player.shoot();
        }
    }

    @Override
    public void update() {
        player.update();

        for (Asteroid asteroid : asteroidList) {
            asteroid.checkBulletCollision();
            asteroid.update();
        }

        asteroidList.removeAll(Asteroid.tempList);
        Asteroid.tempList.clear();

        for (Bullet bullet : bulletList) {
            bullet.update();
        }

        if (asteroidList.size() == 0)
        {
            spawnAsteroids(5);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        player.render(g2d);

        for (Asteroid asteroid : asteroidList) {
            asteroid.render(g2d);
        }

        for (Bullet bullet : bulletList) {
            bullet.render(g2d);
        }
    }
}