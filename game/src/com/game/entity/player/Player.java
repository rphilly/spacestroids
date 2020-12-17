package com.game.entity.player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    private BufferedImage player;

    public float x, y;

    public Player() {
        try {
            player = ImageIO.read(new File("res/entity/player/player2.png"));
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void update() {
    }

    public void render(Graphics g) {
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(player, (int) x, (int) y, player.getWidth() / 2, player.getHeight() / 2,null);
        //g2d.dispose();
    }
}
