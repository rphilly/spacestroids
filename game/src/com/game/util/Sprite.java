package com.game.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Sprite {

    private final BufferedImage SPRITE_SHEET;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 64;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;

    public static Font currentFont;

    public Sprite(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        SPRITE_SHEET = loadSprite(file);

        wSprite = SPRITE_SHEET.getWidth() / w; //total no. of sprites on screen
        hSprite = SPRITE_SHEET.getHeight() / h;
        loadSpriteArray();
    }

    public Sprite(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        SPRITE_SHEET = loadSprite(file);

        wSprite = SPRITE_SHEET.getWidth() / w; //total no. of sprites on screen
        hSprite = SPRITE_SHEET.getHeight() / h;
        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wSprite = SPRITE_SHEET.getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hSprite = SPRITE_SHEET.getHeight() / h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
        } catch(Exception e) {
            System.out.println("SPRITE ERROR: " + file);
        }

        return sprite;
    }

    private void loadSpriteArray() {
        spriteArray = new BufferedImage[wSprite][hSprite]; //No. of sprites in sheet

        for (int x = 0; x < wSprite; x++) {
            for (int y = 0; y < hSprite; y++) {
                spriteArray[x][y] = getSprite(x, y);
            }
        }
    }

    public BufferedImage getSpriteSheet() {
        return SPRITE_SHEET;
    }

    public BufferedImage getSprite(int x, int y) {
        return SPRITE_SHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage[] getSpriteArray(int i) { //get sprite tile
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray2(int i) { //return whole sprite array
        return spriteArray;
    }

    public static void drawArray(Graphics2D g, String word, Vector2f pos, int size, int xOffset) {
        drawArray(g, currentFont, word, pos, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (BufferedImage bufferedImage : img) {
            if (bufferedImage != null) {
                g.drawImage(bufferedImage, (int) x, (int) y, width, height, null);
            }
        }

        x += xOffset;
        y += yOffset;
    }

    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        double x = pos.x;
        double y = pos.y;

        currentFont = f;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                g.drawImage(f.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);

            x += xOffset;
            y += yOffset;
        }
    }
}
