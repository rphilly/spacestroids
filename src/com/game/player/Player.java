package com.game.player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Object2d {

    private BufferedImage player;
    private float x;
    private float y;
    private float velX;
    private float velY;

    protected Sprite sprite;

    public Player() {
        try {
            player = ImageIO.read(new File("res/entity/player/player.png"));
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public Player(float x, float y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public Player(int x, int y) {

    }

    public void update() {
        System.out.println("printing");
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(player, (int) x, (int) y, 250, 250,null);
        g2d.dispose();
    }
}
