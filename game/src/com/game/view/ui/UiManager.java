package com.game.view.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UiManager {

    private final ArrayList<UiObject> objects;

    public UiManager() {
        objects = new ArrayList<>();
    }

    public void tick() {
        for (UiObject o : objects)
            o.tick();
    }

    public void render(Graphics g) {
        for (UiObject o : objects)
            o.render(g);
    }

    public void onMouseMove(MouseEvent e) {
        for (UiObject o : objects)
            o.onMouseMove(e);
    }

    public void onMouseRelease(MouseEvent e) {
        for (UiObject o : objects)
            o.onMouseRelease(e);
    }

    public void addObject(UiObject o) {
        objects.add(o);
    }

    public void removeObject(UiObject o) {
        objects.remove(o);
    }

}
