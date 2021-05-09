package com.game.state;

import com.game.engine.view.Panel;
import com.game.state.ui.UiButton;
import com.game.util.FontLoader;
import com.game.util.MouseHandler;
import com.game.util.SpriteLoader;
import com.game.util.Vector2f;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles player input name.
 */
public class Name extends State {

    public static String name = "";
    private int tick = 0;
    private static final int CHAR_LIMIT = 16;

    public Name(Panel panel) {
        super(panel);
        panel.getMouseHandler().setUiManager(uiManager);

        //Back
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 520, 140, 45, () -> {
            setState(new Menu(panel));
        }));

        KeyListener[] listeners = new KeyListener[panel.getKeyListeners().length];
        System.arraycopy(panel.getKeyListeners(), 0, listeners, 0, panel.getKeyListeners().length);

        for (KeyListener listener : listeners) {
            panel.removeKeyListener(listener);
        }

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setState(new Game(panel));
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (name.length() > 0) {
                        name = name.substring(0, name.length() - 1);
                    }
                } else {
                    if (name.length() < CHAR_LIMIT && (Character.isAlphabetic(e.getKeyChar()) || Character.isDigit(e.getKeyChar()) || e.getKeyCode() == KeyEvent.VK_SPACE)){
                        name += e.getKeyChar();
                    }
                }
            }
        });
    }

    @Override
    public void input(MouseHandler mouse) {
    }

    @Override
    public void update() {
        tick += 1;
    }

    @Override
    public void render(Graphics2D g2d) {
        boolean bar = (tick / 35) % 2 == 0;

        g2d.setColor(Color.white);
        if (bar) {
            g2d.fillRect((int) (panel.getWidth() / 2 - 16 * name.length() * 0.5f + 14 * name.length()), panel.getHeight() / 2, 5, 28);
        }

        g2d.drawRect((panel.getWidth() / 2 - 8 * CHAR_LIMIT - 20), panel.getHeight() / 2 - 12, 16 * CHAR_LIMIT + 40, 48);
        FontLoader.drawFont(g2d, name, new Vector2f((float) panel.getWidth() / 2, (float) panel.getHeight() / 2), 0.5f, 14, 0);

        FontLoader.drawFont(g2d, "NAME", new Vector2f((float) panel.getWidth() / 2, 100), 0.75f,22, 0);
        FontLoader.drawFont(g2d, "Back", new Vector2f((float) panel.getWidth() / 2, 529), 0.5f,14, 0);
    }
}
