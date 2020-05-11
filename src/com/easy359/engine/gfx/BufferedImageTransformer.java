package com.easy359.engine.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedImageTransformer {

    public static BufferedImage highlight(BufferedImage img, int color) {
        BufferedImage newImg = BufferedImageCreator.deepCopy(img);
        BufferedImage highlight = BufferedImageCreator.createRectangularImage(color, img.getWidth(), img.getHeight(),
                Transparency.TRANSLUCENT);
        newImg.getGraphics().drawImage(highlight, 0, 0, null);
        return newImg;
    }

}
