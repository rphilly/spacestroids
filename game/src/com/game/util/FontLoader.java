package com.game.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class FontLoader {

    private final BufferedImage FONT_SHEET;
    private final int width;
    private final int height;
    private final int letterWidth;
    private final int letterHeight;

    public FontLoader(String file, int width, int height) {
        this.width = width;
        this.height = height;

        FONT_SHEET = loadFont(file);

        letterWidth = FONT_SHEET.getWidth() / width;
        letterHeight = FONT_SHEET.getHeight() / height;
    }

    private BufferedImage loadFont(String file) {
        BufferedImage i = null;
        try {
            i = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource(file)));
        } catch (Exception e) {
            System.out.println("Error: font " + file + " failed to load..." + e);
        }

        return i;
    }

    public BufferedImage getLetter(int x, int y) {
        return FONT_SHEET.getSubimage(x * width, y * height, width, height);
    }

    public BufferedImage getLetter(char letter) {
        if (Character.isDigit(letter)) {
            switch(letter) {
                case '0':
                    return getLetter(0, 6);
                case '1':
                    return getLetter(1, 6);
                case '2':
                    return getLetter(2, 6);
                case '3':
                    return getLetter(3, 6);
                case '4':
                    return getLetter(4, 6);
                case '5':
                    return getLetter(5, 6);
                case '6':
                    return getLetter(6, 6);
                case '7':
                    return getLetter(7, 6);
                case '8':
                    return getLetter(8, 6);
                case '9':
                    return getLetter(9, 6);
            }
        } else {
            int value = letter - 65;
            int x = value % letterWidth;
            int y = value / letterWidth;

            return getLetter(x, y);
        }

        return null;
    }

    public int getLetterWidth() {
        return letterWidth;
    }

    public int getLetterHeight() {
        return letterHeight;
    }
}
