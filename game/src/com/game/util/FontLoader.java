package com.game.util;

import com.game.engine.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Enum to store and layout application font-sheet.
 */
public enum FontLoader {
    FONT("graphics/font/font_sheet.png", 16, 16);

    private final BufferedImage FONT_SHEET;
    private final int width;
    private final int height;
    private final int letterWidth;
    private final int letterHeight;

    FontLoader(String file, int width, int height) {
        this.width = width;
        this.height = height;

        FONT_SHEET = ImageLoader.load(file);

        letterWidth = FONT_SHEET.getWidth() / width;
        letterHeight = FONT_SHEET.getHeight() / height;
    }

    public static void drawFont(Graphics2D g2d, String word, Vector2f position, float sizeFactor, int xOffset, int yOffset) {
        double x = position.x;
        double y = position.y;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                g2d.drawImage(FONT.getLetter(word.charAt(i)),
                        (int) (x - 16 * word.length() * sizeFactor),
                        (int) y,
                        (int) (sizeFactor * 64),
                        (int) (sizeFactor * 64),
                        null);

            x += xOffset;
            y += yOffset;
        }
    }

    public BufferedImage getLetter(int x, int y) {
        return FONT_SHEET.getSubimage(x * width, y * height, width, height);
    }

    public BufferedImage getLetter(char letter) {
        if (Character.isDigit(letter)) {
            return getLetter(letter - 48, 6); //ascii
        } else {
            int value = letter - 65;
            int x = value % letterWidth;
            int y = value / letterWidth;

            return getLetter(x, y);
        }
    }

    public int getLetterWidth() {
        return letterWidth;
    }

    public int getLetterHeight() {
        return letterHeight;
    }
}
