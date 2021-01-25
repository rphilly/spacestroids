package com.game.util;

import com.game.view.Panel;
import com.game.view.ui.UiManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    protected int x;
    protected int y;
    private static int mouseB;
    public Point pointer;

    private UiManager uiManager;

    public void setUiManager(UiManager uiManager) {
        this.uiManager = uiManager;
    }

    public Mouse(Panel panel) {
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        pointer = e.getPoint();

        //System.out.println(x + ", " + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getPointer() {
        return pointer;
    }

    public int getButton() {
        return mouseB;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (uiManager != null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();

        if (uiManager != null)
            uiManager.onMouseMove(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;

        if (uiManager != null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
