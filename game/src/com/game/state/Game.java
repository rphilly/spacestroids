package com.game.state;

import com.game.entity.*;
import com.game.util.SaveScore;
import com.game.util.SpriteLoader;
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
    private int round = 1;

    public Game(Panel panel) {
        super(panel);
        initialiseEntities();

        player = new Player(new Vector2f((float) Entity.WIDTH / 2, (float) Entity.HEIGHT / 2), new Vector2f(100, 100),0, this);

        setupAsteroids(15);
    }

    private void initialiseEntities() {
        entityList = new ArrayList<>();
        asteroidList = new ArrayList<>();
        bulletList = new ArrayList<>();
    }

    private void setupAsteroids(int amount) {
        Random random = new Random();
        int low = 35;
        int high = 80;

        for (int i = 0; i < amount; i++) {
            int x = random.nextInt(Entity.WIDTH);
            int y = random.nextInt(Entity.HEIGHT);

            while (Math.abs(x - player.getPosition().x) < high + player.getSize().x) {
                x = random.nextInt(Entity.WIDTH);
            }

            while (Math.abs(y - player.getPosition().y) < high + player.getSize().y) {
                y = random.nextInt(Entity.HEIGHT);
            }

            float differenceX = (float) ((Math.random() * 2 - 1) * Math.sqrt(round));
            float differenceY = (float) ((Math.random() * 2 - 1) * Math.sqrt(round));

            int randSize = random.nextInt(high-low) + low;

            Vector2f position = new Vector2f(x, y);
            Vector2f velocity = new Vector2f(differenceX, differenceY);
            Vector2f size = new Vector2f(randSize, randSize);

            new Asteroid(position, velocity, size, Math.random() * 2 * Math.PI,this);
        }
    }

    public void setupScore() {
        new Thread(()->{
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SaveScore score = new SaveScore();
            score.write(Integer.toString(Asteroid.killcount));
            setState(new Hiscores(panel));
        }).start();
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
        player.checkCollision();

        for (Asteroid asteroid : asteroidList) {
            asteroid.checkBulletCollision();
            asteroid.update();
        }

        for (Bullet bullet : bulletList) {
            bullet.update();
        }

        Asteroid.tempList.forEach(Asteroid::remove);
        Asteroid.tempList.clear();

        Explosion.tempList.forEach(Entity::remove);
        Explosion.tempList.clear();

        if (asteroidList.size() == 0) { //Temp
            round++;
            setupAsteroids(5);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        for (Entity entity : entityList) {
            entity.render(g2d);
        }

        SpriteLoader.drawFont(g2d, Integer.toString(round), new Vector2f((float) panel.getWidth() / 2, 30), 0.75f,26, 0);
        SpriteLoader.drawFont(g2d, Integer.toString(Asteroid.killcount), new Vector2f(50, panel.getHeight() - 100), 0.5f,18, 0);
    }
}