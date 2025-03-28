package com.game.state;

import com.game.engine.math.Vector2f;
import com.game.engine.view.Panel;
import com.game.state.ui.UiButton;
import com.game.util.FontLoader;
import com.game.util.MouseHandler;

import java.awt.*;

/**
 * Handles toggles for options within menu.
 */
public class Options extends State {

    public Options(Panel panel) {
        super(panel);
        panel.getMouseHandler().setUiManager(uiManager);

        //FPS
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 326, 140, 45, () -> {
            panel.showFPS = !panel.showFPS;
        }));

        //Back
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 520, 140, 45, () -> {
            setState(new Menu(panel));
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

        FontLoader.drawFont(g2d, "OPTIONS", new Vector2f((float) panel.getWidth() / 2, 100), 0.75f,22, 0);
        FontLoader.drawFont(g2d, "Show FPS", new Vector2f((float) panel.getWidth() / 2, 335), 0.5f,14, 0);
        FontLoader.drawFont(g2d, "Back", new Vector2f((float) panel.getWidth() / 2, 529), 0.5f,14, 0);
    }
}
