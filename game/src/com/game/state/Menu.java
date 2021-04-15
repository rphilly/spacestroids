package com.game.state;

import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.ui.UiButton;
import com.game.ui.UiManager;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;

import java.awt.*;

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
    }

    @Override
    public void render(Graphics2D g2d) {
        uiManager.render(g2d);

        java.awt.Font f = new Font("Arial", java.awt.Font.BOLD, 64);
        g2d.setFont(f);
        g2d.setColor(Color.WHITE);

        FontMetrics fm = g2d.getFontMetrics();

        int x = panel.getWidth() / 2 - fm.stringWidth("SPACESTROIDS") / 2;
        int y = panel.getHeight() / 2 - fm.getHeight();

        //g.drawString(s, x, y); //centered

        Sprite.drawArray(g2d, font, "SPACESTROIDS", new Vector2f((float) panel.getWidth() / 2 - 192, 100), 64, 64, 30, 0); //230
        //g2d.drawRect(panel.getWidth() / 2 - 192, 100, 360, 64);
        //System.out.println(font.getWidth() + ", h: " + font.getHeight());
        Sprite.drawArray(g2d, font, "Play", new Vector2f((float) panel.getWidth() / 2 - 41, 270), 32, 32, 14, 0);
        Sprite.drawArray(g2d, font, "Hiscores", new Vector2f((float) panel.getWidth() / 2 - 70, 335), 32, 32, 14, 0);
        Sprite.drawArray(g2d, font, "Options", new Vector2f((float) panel.getWidth() / 2 - 60, 400), 32, 32, 14, 0);
        Sprite.drawArray(g2d, font, "Exit", new Vector2f((float) panel.getWidth() / 2 - 41, 465), 32, 32, 14, 0);

        //g2d.setColor(Color.GREEN);
        //g2d.drawLine(panel.getWidth() / 2, panel.getHeight(), panel.getWidth() / 2, 0);
    }
}
