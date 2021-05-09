package com.game.state.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * User-interface class.
 *
 * Designs properties of gui objects.
 */
public abstract class UiObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering;

    public UiObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        hovering = bounds.contains(e.getX(), e.getY());
    }

    public void onMouseRelease(MouseEvent e) { //NOTE: checks all buttons
        if (hovering)
            onClick();
    }
}
