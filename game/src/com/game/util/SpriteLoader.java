package com.game.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteLoader {

    private final BufferedImage sprite;
    private final int width;
    private final int height;

    public SpriteLoader(String file) {
        sprite = ImageLoader.load(file);

        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    public static void drawFont(Graphics2D g2d, String word, Vector2f position, float sizeFactor, int xOffset, int yOffset) {
        double x = position.x;
        double y = position.y;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                g2d.drawImage(FontLoader.FONT.getLetter(word.charAt(i)),
                        (int) (x - 16 * word.length() * sizeFactor),
                        (int) y,
                        (int) (sizeFactor * 64),
                        (int) (sizeFactor * 64),
                        null);

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
