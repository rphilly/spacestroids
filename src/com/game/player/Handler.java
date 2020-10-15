package com.game.player;

import com.game.player.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Handler implements MouseListener, MouseMotionListener {

    //Player player = new Player();

    Graphics g;
    Panel panel;

    Point initialClick;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        initialClick = mouseEvent.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        /*float thisX = player.getX();
        float thisY = player.getY();

        float xMoved = (thisX + mouseEvent.getX()) - (thisX + initialClick.x);
        float yMoved = (thisY + mouseEvent.getY()) - (thisY + initialClick.y);

        float X = thisX + xMoved;
        float Y = thisY + yMoved;

        player.setLocation(X, Y);
        System.out.println(player.getLocation());
        //panel.repaint();*/
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
