package com.game.state;

import com.game.engine.view.Panel;
import com.game.state.ui.UiButton;
import com.game.state.ui.UiManager;
import com.game.util.MouseHandler;
import com.game.util.SaveScore;
import com.game.util.SpriteLoader;
import com.game.util.Vector2f;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Hiscores extends State {

    private final SaveScore score = new SaveScore();

    private final UiManager uiManager;

    public Hiscores(Panel panel) {
        super(panel);

        uiManager = new UiManager();
        panel.getMouseHandler().setUiManager(uiManager);

        //Back
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 456, 140, 45, () -> {
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

        SpriteLoader.drawFont(g2d, "HISCORES", new Vector2f((float) panel.getWidth() / 2 - 150, 100), 48, 48, 22, 0);

        ArrayList<Integer> scoreList = score.getHiscores();
        g2d.drawString("Score: ", 608, 210);

        for (int i = 0; i < scoreList.size(); i++) {
            g2d.drawString(i + 1 + ". " + scoreList.get(i), 620, 265 + i * 25);
        }

        SpriteLoader.drawFont(g2d, "Back", new Vector2f((float) panel.getWidth() / 2 - 41, 465), 32, 32, 14, 0);
    }
}
