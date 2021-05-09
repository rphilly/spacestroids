package com.game.state;

import com.game.state.ui.UiManager;
import com.game.util.MouseHandler;
import com.game.engine.view.Panel;

import java.awt.Graphics2D;

/**
 * Default state class to design properties of each extending state.
 */
public abstract class State {

    public Panel panel;
    protected UiManager uiManager;
    private static State currentState = null;

    public State(Panel panel) {
        this.panel = panel;
        this.uiManager = new UiManager();
    }

    public abstract void input(MouseHandler mouse);

    public abstract void update();

    public abstract void render(Graphics2D g2d);

    public static State getState() {
        return currentState;
    }

    public static void setState(State state) {
        currentState = state;
    }
}
