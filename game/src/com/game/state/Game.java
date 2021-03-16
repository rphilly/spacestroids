package com.game.state;

import com.game.util.Font;
import com.game.world.entity.Asteroid;
import com.game.world.entity.Entity;
import com.game.world.entity.Player;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends State {

    Player player;
    ArrayList<Asteroid> enemies = new ArrayList<>();

    public Game(Panel panel) {
        super(panel);

        player = new Player(new Sprite("entity/player/player4.png"), new Vector2f(300, 300));

        initialiseEnemy(3);
    }

    @Override
    public void input(Mouse mouse) {
        player.input(mouse);
    }

    @Override
    public void update() {
        for (Entity temp : enemies) {
            temp.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        player.render(g);

        for (Entity temp : enemies) {
            temp.render(g);
        }

        String score = "Score 5";
        Sprite.drawArray(g, score, new Vector2f((float) 1280 / 2 - 75, 40), 40, 20);
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
