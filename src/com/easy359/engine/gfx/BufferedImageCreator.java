package com.easy359.engine.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * Created by Ian on 1/17/2016.
 */
public class BufferedImageCreator {

    private BufferedImageCreator() {
    }

    public static BufferedImage createImageFromFilePath(String path, int x, int y, int width, int height) {
	BufferedImage image = null;
	try {
	    File file = new File(path);
	    image = ImageIO.read(file);
	    image = image.getSubimage(x, y, width, height);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return image;
    }

    public static BufferedImage createImageFromPixels(int[] pixels, int width, int height, int transparency) {
	BufferedImage image = new BufferedImage(width, height, transparency);
	image.setRGB(0, 0, width, height, pixels, 0, width);
	return image;
    }

    public static BufferedImage createRectangularImage(int color, int width, int height, int transparency) {
	BufferedImage image = new BufferedImage(width, height, transparency);
	int[] pixels = new int[width * height];
	Arrays.fill(pixels, color);
	image.setRGB(0, 0, width, height, pixels, 0, width);
	return image;
    }

    public static BufferedImage createOutlinedRectangularImage(int outlineColor, int backgroundColor, int width,
	    int height, int transparency) {
	BufferedImage image = new BufferedImage(width, height, transparency);
	int[] pixels = new int[width * height];
	Arrays.fill(pixels, backgroundColor);
	for (int x = 0; x < width; x++) {
	    pixels[x] = outlineColor;
	    pixels[x + (width * (height - 1))] = outlineColor;
	}
	for (int y = 0; y < height; y++) {
	    pixels[y * width] = outlineColor;
	    pixels[(y * width) + (width - 1)] = outlineColor;
	}
	image.setRGB(0, 0, width, height, pixels, 0, width);
	return image;
    }

    public static BufferedImage deepCopy(BufferedImage img) {
	if (img == null)
	    return null;
	ColorModel cm = img.getColorModel();
	boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
	WritableRaster raster = img.copyData(null);
	return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
