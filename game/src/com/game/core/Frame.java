package com.game.core;

import javax.swing.JFrame;

public class Frame extends JFrame {

    private final String NAME = "GAME!";
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
