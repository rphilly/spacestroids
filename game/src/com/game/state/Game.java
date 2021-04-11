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

        player = new Player(new Vector2f(100, 100), this);

        initialiseEnemy(1);
    }

    void setupEntities() {
        entityList = new ArrayList<>();
        asteroidList = new ArrayList<>();
        bulletList = new ArrayList<>();
    }

    public void initialiseEnemy(int amount) {
        Random random = new Random();

        int x = 0;
        int y = 0;

        if (random.nextInt() > 0.5) {
            x = random.nextInt(panel.getWidth() - 100);
        } else {
            y = random.nextInt(panel.getHeight() - 100);
        }

        int randomSize = 32 + random.nextInt(64);

        for (int i = 0; i <= amount; i++) {
            asteroidList.add(new Asteroid(new Vector2f(x, y), this));
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
