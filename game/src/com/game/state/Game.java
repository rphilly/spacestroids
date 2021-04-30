package com.game.state;

import com.game.entity.*;
import com.game.util.SpriteLoader;
import com.game.util.MouseHandler;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends State {

    public ArrayList<Entity> entityList;
    public ArrayList<Asteroid> asteroidList;
    public ArrayList<Bullet> bulletList;
    private final Player player;

    private int round = 1;
    public boolean attack;

    public Game(Panel panel) {
        super(panel);
        setupEntities();
        player = new Player(new Vector2f((float) panel.getWidth() / 2, (float) panel.getHeight() / 2),
                            new Vector2f(100, 100), 0, this);

        generateAsteroids(1);
        Asteroid.setKillcount(0); //???
    }

    private void setupEntities() {
        entityList = new ArrayList<>();
        asteroidList = new ArrayList<>();
        bulletList = new ArrayList<>();
    }

    public void setupScore() {
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("Error: hiscores failed to load..." + e);
            }

            Hiscores.write(Integer.toString(Asteroid.killcount), round);
            setState(new Hiscores(panel));

        }).start();
    }

    private void generateAsteroids(int amount) {
        Random random = new Random();
        int low = 35;
        int high = 80;

        for (int i = 0; i < amount; i++) {
            int x = random.nextInt(panel.getWidth());
            int y = random.nextInt(panel.getHeight());

            while (Math.abs(x - player.getPosition().x) < high + player.getSize().x && Math.abs(y - player.getPosition().y) < high + player.getSize().y) {
                x = random.nextInt(panel.getWidth());
                y = random.nextInt(panel.getHeight());
            }

            float differenceX = (float) ((Math.random() * 2 - 1) * Math.sqrt(round));
            float differenceY = (float) ((Math.random() * 2 - 1) * Math.sqrt(round));

            int randSize = random.nextInt(high - low) + low;

            Vector2f position = new Vector2f(x, y);
            Vector2f velocity = new Vector2f(differenceX, differenceY);
            Vector2f size = new Vector2f(randSize, randSize);

            new Asteroid(position, velocity, size, Math.random() * 2 * Math.PI, this);
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
        player.checkAsteroidCollision();

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

        if (asteroidList.isEmpty()) {
            round++;
            generateAsteroids(round);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        for (Entity entity : entityList) {
            entity.render(g2d);
        }

        SpriteLoader.drawFont(g2d, Integer.toString(round), new Vector2f((float) panel.getWidth() / 2, 30), 0.75f, 26, 0);
        SpriteLoader.drawFont(g2d, Integer.toString(Asteroid.killcount), new Vector2f(50, panel.getHeight() - 100), 0.5f, 18, 0);
        SpriteLoader.drawFont(g2d, Name.name, new Vector2f(panel.getWidth() - 150, panel.getHeight() - 100), 0.5f, 12, 0);
    }
}