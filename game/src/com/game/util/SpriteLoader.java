package com.game.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SpriteLoader {

    private final BufferedImage sprite;
    private final int width;
    private final int height;

    public SpriteLoader(String file) {
        sprite = loadSprite(file);

        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage i = null;
        try {
            i = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource(file)));
        } catch (Exception e) {
            System.out.println("Error: sprite " + file + " failed to load..." + e);
        }

        return i;
    }

    public static void drawFont(Graphics2D g2d, String word, Vector2f position, int width, int height, int xOffset, int yOffset) {
        double x = position.x;
        double y = position.y;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                g2d.drawImage(FontLoader.FONT.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);

            x += xOffset;
            y += yOffset;
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
