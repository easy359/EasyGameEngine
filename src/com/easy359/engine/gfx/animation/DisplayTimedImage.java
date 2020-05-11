package com.easy359.engine.gfx.animation;

import com.easy359.engine.gfx.DisplayImage;

import java.awt.image.BufferedImage;

public class DisplayTimedImage extends DisplayImage {

    private double delay;

    public DisplayTimedImage(BufferedImage img, double x, double y, int zIndex, double delay) {
        super(img, x, y, zIndex);
        this.delay = delay;
    }

    public DisplayTimedImage(DisplayImage img, double delay) {
        this(img.getImageCopy(), img.getX(), img.getY(), img.getZIndex(), delay);
    }

    public DisplayTimedImage(DisplayTimedImage other) {
        this(other, other.delay);
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }
}
