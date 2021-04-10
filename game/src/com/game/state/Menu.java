package com.game.state;

import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.ui.UiButton;
import com.game.ui.UiManager;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;
import com.game.world.entity.Asteroid;

import java.awt.*;
import java.util.ArrayList;

public class Menu extends State {

    private final UiManager uiManager;

    public Menu(Panel panel) {
        super(panel);

        uiManager = new UiManager();
        panel.getMouseHandler().setUiManager(uiManager);

        //Play
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 260, 140, 45, () -> {
            panel.getMouseHandler().setUiManager(null);
            setState(new Game(panel));
        }));

        //Hi-scores
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 326, 140, 45, () -> {
            //setState(new DeathState(panel));
        }));

        //Options
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 392, 140, 45, () -> {
            setState(new Options(panel));
        }));

        //Exit
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 456, 140, 45, () -> {
            System.exit(0);
        }));
    }

    @Override
    public void input(Mouse mouse) {

    }

    @Override
    public void update() {
        uiManager.tick();

        //System.out.println(game.getMouseHandler().getX() + "   " + game.getMouseHandler().getY());
    }

    int x, y;
    String s = "SPACESTROIDS";

    @Override
    public void render(Graphics2D g) {
        uiManager.render(g);

        java.awt.Font f = new Font("Arial", java.awt.Font.BOLD, 64);
        g.setFont(f);
        g.setColor(Color.WHITE);

        FontMetrics fm = g.getFontMetrics();

        x = panel.getWidth() / 2 - fm.stringWidth(s) / 2;
        y = panel.getHeight() / 2 - fm.getHeight();

        //g.drawString(s, x, y); //centered

        Sprite.drawArray(g, font, "SPACESTROIDS", new Vector2f((float) panel.getWidth() / 2 - 190, 100), 64, 64, 30, 0); //230
        Sprite.drawArray(g, font, "Play", new Vector2f((float) panel.getWidth() / 2 - 41, 270), 32, 32, 14, 0);
        Sprite.drawArray(g, font, "Hiscores", new Vector2f((float) panel.getWidth() / 2 - 70, 335), 32, 32, 14, 0);
        Sprite.drawArray(g, font, "Options", new Vector2f((float) panel.getWidth() / 2 - 60, 400), 32, 32, 14, 0);
        Sprite.drawArray(g, font, "Exit", new Vector2f((float) panel.getWidth() / 2 - 41, 465), 32, 32, 14, 0);

        g.setColor(Color.GREEN);
        g.drawLine(panel.getWidth() / 2, panel.getHeight(), panel.getWidth() / 2, 0);
    }
}
