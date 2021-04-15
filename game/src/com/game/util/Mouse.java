package com.game.util;

import com.game.engine.view.Panel;
import com.game.ui.UiManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    protected int x;
    protected int y;
    private static int mouseB;
    private static int rightClick;
    public Point pointer;

    private UiManager uiManager;

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

        if (uiManager != null)
            uiManager.onMouseMove(e);

        //System.out.println(x + ", " + y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        rightClick = -1;

        if (uiManager != null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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

    public int getRightClick() {
        return rightClick;
    }

    public void setUiManager(UiManager uiManager) {
        this.uiManager = uiManager;
    }
}
