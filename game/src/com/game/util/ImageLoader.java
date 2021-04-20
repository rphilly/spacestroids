package com.game.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ImageLoader {

    private ImageLoader() {} //Prevents creating new object of this type

    public static BufferedImage load(String file) {
        BufferedImage i = null;
        try {
            i = ImageIO.read(Objects.requireNonNull(ImageLoader.class.getClassLoader().getResource(file)));
        } catch (Exception e) {
            System.out.println("Error: sprite " + file + " failed to load..." + e);
        }

        return i;
    }
}
