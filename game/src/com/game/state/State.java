package com.game.state;

import com.game.util.Font;
import com.game.util.Mouse;
import com.game.engine.view.Panel;

import java.awt.Graphics2D;

public abstract class State {

    protected Panel panel;
    private static State currentState = null;

    Font font = new Font("graphics/font/font_sheet.png", 16, 16);

    public State(Panel panel) {
        this.panel = panel;
    }

    public static State getState() {
        return currentState;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public abstract void input(Mouse mouse);

    public abstract void update();

    public abstract void render(Graphics2D g);

}
