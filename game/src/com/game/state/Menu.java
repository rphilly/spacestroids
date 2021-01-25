package com.game.state;

import com.game.util.Font;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.view.ui.UiButton;
import com.game.view.ui.UiManager;
import com.game.util.Vector2f;
import com.game.view.Panel;

import java.awt.*;

public class Menu extends State {

    private final UiManager uiManager;

    Font font = new Font("graphics/font/fontsheet.png", 16, 16);

    public Menu(Panel panel) {
        super(panel);

        uiManager = new UiManager();
        panel.getMouseHandler().setUiManager(uiManager);

        //Play
        uiManager.addObject(new UiButton((float) Panel.width / 2 - 70, 260, 140, 45, () -> {
            panel.getMouseHandler().setUiManager(null);
            setState(new Game(panel));
        }));

        //Hi-scores
        uiManager.addObject(new UiButton((float) Panel.width / 2 - 70, 326, 140, 45, () -> {
            //setState(new DeathState(panel));
        }));

        //Options
        uiManager.addObject(new UiButton((float) Panel.width / 2 - 70, 392, 140, 45, () -> {
            //System.exit(0);
        }));

        //Exit
        uiManager.addObject(new UiButton((float) Panel.width / 2 - 70, 456, 140, 45, () -> {
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

    @Override
    public void render(Graphics2D g) {
        uiManager.render(g);
        Sprite.drawArray(g, font, "GAME TITLE HERE", new Vector2f((float) Panel.width / 2 - 230, 100), 64, 64, 32, 0);
        Sprite.drawArray(g, font, "Play", new Vector2f((float) Panel.width / 2 - 41, 270), 32, 32, 16, 0);
        Sprite.drawArray(g, font, "Hiscores", new Vector2f((float) Panel.width / 2 - 70, 335), 32, 32, 16, 0);
        Sprite.drawArray(g, font, "Options", new Vector2f((float) Panel.width / 2 - 60, 400), 32, 32, 16, 0);
        Sprite.drawArray(g, font, "Exit", new Vector2f((float) Panel.width / 2 - 41, 465), 32, 32, 16, 0);
    }
}
