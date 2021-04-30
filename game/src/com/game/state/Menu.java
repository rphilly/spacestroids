package com.game.state;

import com.game.entity.Asteroid;
import com.game.entity.Entity;
import com.game.util.MouseHandler;
import com.game.state.ui.UiButton;
import com.game.engine.view.Panel;
import com.game.util.SpriteLoader;
import com.game.util.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends State {

    private final State name = new Name(panel);
    ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();

    public Menu(Panel panel) {
        super(panel);
        panel.getMouseHandler().setUiManager(uiManager);

        //Play
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 260, 140, 45, () -> {
            panel.getMouseHandler().setUiManager(null);
            setState(name);
        }));

        //Hi-scores
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 326, 140, 45, () -> {
            setState(new Hiscores(panel));
        }));

        //Options
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 392, 140, 45, () -> {
            setState(new Options(panel));
        }));

        //Exit
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 520, 140, 45, () -> { //456
            System.exit(0);
        }));

        generateAsteroids();
    }

    private void generateAsteroids() {
        Random random = new Random();
        int low = 35;
        int high = 80;

        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(panel.getWidth());
            int y = random.nextInt(panel.getHeight());

            float differenceX = (float) ((Math.random() * 2 - 1));
            float differenceY = (float) ((Math.random() * 2 - 1));

            int randSize = random.nextInt(high - low) + low;

            Vector2f position = new Vector2f(x, y);
            Vector2f velocity = new Vector2f(differenceX, differenceY);
            Vector2f size = new Vector2f(randSize, randSize);

            asteroidList.add(new Asteroid(position, velocity, size, Math.random() * 2 * Math.PI));
        }
    }

    @Override
    public void input(MouseHandler mouse) {
    }

    @Override
    public void update() {
        uiManager.update();

        for (Asteroid asteroid : asteroidList) {
            asteroid.move();
            asteroid.setupWrap(this);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        for (Entity entity : asteroidList) {
            entity.render(g2d);
        }

        uiManager.render(g2d);

        SpriteLoader.drawFont(g2d,"SPACESTROIDS", new Vector2f((float) panel.getWidth() / 2, 100), 1, 30, 0);
        SpriteLoader.drawFont(g2d,"Play", new Vector2f((float) panel.getWidth() / 2, 270), 0.5f, 14, 0);
        SpriteLoader.drawFont(g2d,"Hiscores", new Vector2f((float) panel.getWidth() / 2, 335),0.5f,14, 0);
        SpriteLoader.drawFont(g2d,"Options", new Vector2f((float) panel.getWidth() / 2, 400), 0.5f,14, 0);
        SpriteLoader.drawFont(g2d,"Exit", new Vector2f((float) panel.getWidth() / 2, 529), 0.5f,14, 0);
    }
}
