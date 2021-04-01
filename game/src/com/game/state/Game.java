package com.game.state;

import com.game.world.entity.Asteroid;
import com.game.world.entity.Bullet;
import com.game.world.entity.Entity;
import com.game.world.entity.Player;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game extends State {

    Player player;

    LinkedList<Entity> objects = new LinkedList<>();
    ArrayList<Asteroid> enemies = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    public Game(Panel panel) {
        super(panel);

        player = new Player(new Vector2f(300, 300), this);

        initialiseEnemy(1);
    }

    @Override
    public void input(Mouse mouse) {
        player.input(mouse);
    }

    @Override
    public void update() {
        player.update();

        for (Entity temp : enemies) {
            temp.update();
        }

        for (Entity temp : bullets) {
            temp.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        player.render(g);

        for (Entity temp : enemies) {
            temp.render(g);
        }


        for (Entity temp : bullets) {
            temp.render(g);
        }

        //String score = "Score 5";
        //Sprite.drawArray(g, score, new Vector2f((float) 1280 / 2 - 75, 40), 40, 20);
        //Sprite.drawArray(g, font, "Score", new Vector2f((float) panel.getWidth() / 2 - 41, 465), 32, 32, 16, 0);
    }

    public void addObj(Entity temp) {
        if (temp.getClass() == Bullet.class){
            bullets.add((Bullet) temp);
        } else {
            objects.add(temp);
        }
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
            enemies.add(new Asteroid(new Vector2f(x, y)));
        }
    }
}
