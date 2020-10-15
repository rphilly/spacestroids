package com.game.player;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Object2d {

    public float x;
    public float y;

    BufferedImage player;

    public Object2d() {
        //player = new Player(50, 50, new Sprite("entity/player/player.png"));
        //new Player();
    }

    public Object2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        //g2D.drawImage(player, (int) x, (int) y, 150, 150, null);
        //g2D.dispose();
    }

    public void update() {

    }
}
