package com.game.player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    private float x;
    private float y;
    private float velX;
    private float velY;

    private BufferedImage player = null;


    public Sprite(String file) {
        System.out.println("Loading: " + file + "...");
        player = loadSprite(file);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

    }

    public void update(long time) {
        x += velX * time;
        y += velY * time;
        //a.update(time);
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch(Exception e) {
            System.out.println("SPRITE ERROR: " + file);
        }
        return sprite;
    }
}
