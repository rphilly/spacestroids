package com.game.state;

import com.game.engine.view.Panel;
import com.game.ui.UiButton;
import com.game.ui.UiManager;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;

import java.awt.*;

public class Options extends State {

    private final UiManager uiManager;

    public Options(Panel panel) {
        super(panel);

        uiManager = new UiManager();
        panel.getMouseHandler().setUiManager(uiManager);

        //Controls
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 260, 140, 45, () -> {
            //
        }));

        //Scale
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 326, 140, 45, () -> {
            panel.showFPS = !panel.showFPS;
        }));

        //Exit
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 456, 140, 45, () -> {
            setState(new Menu(panel));
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
        Sprite.drawArray(g2d, font, "OPTIONS", new Vector2f((float) panel.getWidth() / 2 - 190, 100), 48, 48, 22, 0);
        Sprite.drawArray(g2d, font, "Controls", new Vector2f((float) panel.getWidth() / 2 - 41, 270), 32, 32, 14, 0);
        Sprite.drawArray(g2d, font, "FPS toggler", new Vector2f((float) panel.getWidth() / 2 - 70, 335), 32, 32, 14, 0);
        Sprite.drawArray(g2d, font, " ", new Vector2f((float) panel.getWidth() / 2 - 60, 400), 32, 32, 14, 0);
        Sprite.drawArray(g2d, font, "Back", new Vector2f((float) panel.getWidth() / 2 - 41, 465), 32, 32, 14, 0);
    }
}
