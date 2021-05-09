package com.game.engine;

import com.game.engine.view.Frame;

/**
 * Application launcher class.
 **/
public class Launcher {

    private final Frame frame;
    public static Launcher launcher;

    public Launcher() {
        frame = new Frame();
    }

    public static void main(String[] args) {
        launcher = new Launcher();
    }

    public Frame getFrame() {
        return frame;
    }
}

