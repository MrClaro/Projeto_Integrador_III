package com.lexlabor.views.utils;

import java.awt.image.BufferedImage;

public class ChangeImageColorUtil {

    public static BufferedImage convertToWhite(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null.");
        }

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xFF;
                int whitePixel = (alpha << 24) | (255 << 16) | (255 << 8) | 255;
                newImage.setRGB(x, y, whitePixel);
            }
        }
        return newImage;
    }
}