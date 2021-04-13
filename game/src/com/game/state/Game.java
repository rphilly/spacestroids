package com.game.state;

import com.game.world.entity.Asteroid;
import com.game.world.entity.Bullet;
import com.game.world.entity.Entity;
import com.game.world.entity.Player;
import com.game.util.Mouse;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;

import java.awt.*;
import java.util.ArrayList;

public class Game extends State {

    Player player;

    public ArrayList<Entity> entityList;
    public ArrayList<Asteroid> asteroidList;
    public ArrayList<Bullet> bulletList;

    public boolean attack;

    public Game(Panel panel) {
        super(panel);
        setupEntities();

        player = new Player(new Vector2f((float) panel.getWidth() / 2, (float) panel.getHeight() / 2), new Vector2f(39, 62),0, this);

        new Asteroid(new Vector2f(100, 250), new Vector2f(64, 64), 0,this);
        new Asteroid(new Vector2f(150, 80), new Vector2f(64, 64), 0,this);
        new Asteroid(new Vector2f(200, 0), new Vector2f(64, 64), 0,this);
        new Asteroid(new Vector2f(250, 40), new Vector2f(64, 64), 0,this);
        new Asteroid(new Vector2f(800, 0), new Vector2f(64, 64), 0,this);
    }

    void setupEntities() {
        entityList = new ArrayList<>();
        asteroidList = new ArrayList<>();
        bulletList = new ArrayList<>();
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
    public void render(Graphics2D g2d) {
        player.render(g2d);

        for (Entity asteroid : asteroidList) {
            asteroid.render(g2d);
        }

        for (Entity bullet : bulletList) {
            bullet.render(g2d);
        }
    }
}
