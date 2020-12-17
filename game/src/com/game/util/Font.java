package com.game.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Font {

    private final BufferedImage FONTSHEET;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    private int wLetter;
    private int hLetter;

    public Font(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public Font(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();

    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wLetter = FONTSHEET.getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hLetter = FONTSHEET.getHeight() / h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    private BufferedImage loadFont(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch(Exception e) {
            System.out.println("Error: could not load sprite: " + file);
        }
        return sprite;
    }

    public void loadFontArray() {
        spriteArray = new BufferedImage[wLetter][hLetter]; //Total number of sprites within spritesheet

        for (int x = 0; x < wLetter; x++) {
            for (int y = 0; y < hLetter; y++) {
                spriteArray[x][y] = getLetter(x, y);
            }
        }
    }

    public BufferedImage[] getFont(String word) {

        BufferedImage[] img_word = new BufferedImage[30];

        for(int i = 0; i < word.length(); i++) {
            int a = word.charAt(i);
            if(a != 70) {
                img_word[i] = FONTSHEET.getSubimage(((a - 65) % 6) * 16, ((int) ((a - 65) / 6)) * 16, 16, 16);
            } else {
                img_word[i] = null;
            }

        }

        return img_word;

    }

    public BufferedImage getFontSheet() {
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x, int y) {
        return FONTSHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getLetter(char letter) {
        if(Character.isDigit(letter)) {
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

            int x = value % wLetter;
            int y = value / wLetter;
            return getLetter(x, y);
        }
        return null;
    }
}
