package com.game.state;

import com.game.util.FontLoader;
import com.game.util.MouseHandler;
import com.game.state.ui.UiButton;
import com.game.state.ui.UiManager;
import com.game.engine.view.Panel;
import com.game.util.SpriteLoader;
import com.game.util.Vector2f;

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
            setState(new Hiscores(panel));
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
    public void input(MouseHandler mouse) {

    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void render(Graphics2D g2d) {
        uiManager.render(g2d);

        SpriteLoader.drawFont(g2d,"SPACESTROIDS", new Vector2f((float) panel.getWidth() / 2 - 192, 100), 64, 64, 30, 0); //230
        //g2d.drawRect(panel.getWidth() / 2 - 208, 100, 360, 64);
        //System.out.println(font.getWidth() + ", h: " + font.getHeight());

        SpriteLoader.drawFont(g2d,"Play", new Vector2f((float) panel.getWidth() / 2 - 41, 270), 32, 32, 14, 0);
        //g2d.drawRect(panel.getWidth() / 2 - 41, 270, 56, 32);

        SpriteLoader.drawFont(g2d,"Hiscores", new Vector2f((float) panel.getWidth() / 2 - 70, 335), 32, 32, 14, 0);
        SpriteLoader.drawFont(g2d,"Options", new Vector2f((float) panel.getWidth() / 2 - 60, 400), 32, 32, 14, 0);
        SpriteLoader.drawFont(g2d,"Exit", new Vector2f((float) panel.getWidth() / 2 - 41, 465), 32, 32, 14, 0);

        //g2d.setColor(Color.GREEN);
        //g2d.drawLine(panel.getWidth() / 2, panel.getHeight(), panel.getWidth() / 2, 0);
    }
}
