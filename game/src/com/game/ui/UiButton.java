package com.game.ui;

import java.awt.*;

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
        if (hovering) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillRect((int) x, (int) y, width, height);
        graphics = g;
    }

    @Override
    public void onClick() {
        click.onClick();
    }
}
