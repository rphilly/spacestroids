package com.game.engine.view;

import javax.swing.*;

/**
 * Sets-up application frame and applies the panel.
 */
public class Frame extends JFrame {

    /**
     * Variables to store application information; used within this package only.
     */
    private final String VERSION = "1.02_1";
    protected final String NAME = "Spacestroids " + VERSION;
    protected final int GAME_WIDTH = 1280;
    protected final int GAME_HEIGHT = 720;

    public Frame() {
        setTitle(NAME);
        setContentPane(new Panel(GAME_WIDTH, GAME_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
