package com.game.state;

import com.game.util.SaveScore;
import com.game.util.SpriteLoader;
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

        player = new Player(new Vector2f((float) Entity.WIDTH / 2, (float) Entity.HEIGHT / 2), new Vector2f(100, 100),0, this);
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

            float differenceX = (float) Math.random() * 2 - 1;
            float differenceY = (float) Math.random() * 2 - 1;

            int low = 20;
            int high = 80;
            int randSize = random.nextInt(high-low) + low;

            Vector2f position = new Vector2f(x, y);
            Vector2f velocity = new Vector2f(differenceX, differenceY);
            Vector2f size = new Vector2f(randSize, randSize);

            new Asteroid(position, velocity, size, Math.random() * 2 * Math.PI,this);
        }
    }

    private final SaveScore score = new SaveScore();

    public void playerDeath() {
        score.write(Integer.toString(Asteroid.killcount));
        setState(new Hiscores(panel));
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

        Asteroid.tempList.forEach(Asteroid::remove);
        Asteroid.tempList.clear();

        for (Bullet bullet : bulletList) {
            bullet.update();
        }

        if (asteroidList.size() == 0) {
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

        SpriteLoader.drawFont(g2d, Integer.toString(Asteroid.killcount), new Vector2f(50, panel.getHeight() - 100), 0.5f,22, 0);
    }
}