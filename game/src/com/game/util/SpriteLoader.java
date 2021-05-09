package com.game.util;

import java.awt.image.BufferedImage;

/**
 * Handles loading of in-game sprites.
 */
public class SpriteLoader {

    private final BufferedImage sprite;
    private final int width;
    private final int height;

    public SpriteLoader(String file) {
        sprite = ImageLoader.load(file);

        width = sprite.getWidth();
        height = sprite.getHeight();
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
