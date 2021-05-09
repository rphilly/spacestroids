package com.game.state.ui;

import java.awt.*;

/**
 * User-interface class.
 *
 * Builds buttons used within application menu system.
 */
public class UiButton extends UiObject {

    private final ClickListener click;
    private Graphics graphics;

    public UiButton(float x, float y, int width, int height, ClickListener click) {
        super(x, y, width, height);
        this.click = click;
    }

    @Override
    public void tick() {
        if (hovering) {
            render(graphics);
        }
    }

    @Override
    public void render(Graphics g) {
        if (g == null) {
            System.out.println(this);
            return;
        }

        if (hovering) {
            g.setColor(Color.BLUE);
            g.fillRect((int) x, (int) y, width, height);
        }

        graphics = g;
    }

    @Override
    public void onClick() {
        click.onClick();
    }
}
