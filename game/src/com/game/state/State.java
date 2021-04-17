package com.game.state;

import com.game.util.FontLoader;
import com.game.util.MouseHandler;
import com.game.engine.view.Panel;

import java.awt.Graphics2D;

public abstract class State {

    protected Panel panel;
    private static State currentState = null;

    public State(Panel panel) {
        this.panel = panel;
    }

    public static State getState() {
        return currentState;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public abstract void input(MouseHandler mouse);

    public abstract void update();

    public abstract void render(Graphics2D g2d);

}
