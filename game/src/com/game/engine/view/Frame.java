package com.game.engine.view;

import javax.swing.*;

public class Frame extends JFrame {

    private final String VERSION = "Spacestroids 0.45";
    protected final String NAME = "" + VERSION;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public Frame() {
        setTitle(NAME);
        setContentPane(new Panel(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
