package com.game.engine.view;

import javax.swing.*;

public class Frame extends JFrame {

    protected final String NAME = "GAME!";
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public Frame() {
        setTitle(NAME);
        setContentPane(new Panel(WIDTH, HEIGHT));
        //setContentPane(new Player());
        //getContentPane().add(new Player());
        //setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
