package com.easy359.engine.gfx;

import com.easy359.engine.physics.AABB;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DisplayImage implements Comparable<DisplayImage> {

    private final BufferedImage img;
    private AABB bounds;
    private int zIndex;

    public DisplayImage(BufferedImage img, double x, double y, int zIndex) {
        if (img == null)
            throw new IllegalArgumentException("img cannot be null");
        this.img = BufferedImageCreator.deepCopy(img);
        bounds = new AABB(x, y, img.getWidth(), img.getHeight());
        this.zIndex = zIndex;
    }

    public DisplayImage(DisplayImage other) {
        this(other.img, other.bounds.getX(), other.bounds.getY(), other.zIndex);
    }

    public static void sort(List<DisplayImage> images) {
        if (images == null)
            return;
        Collections.sort(images, new Comparator<DisplayImage>() {
            @Override
            public int compare(DisplayImage img1, DisplayImage img2) {
                if (img1 == null ^ img2 == null) {
                    return (img1 == null) ? img2.compareTo(img1) : img1.compareTo(img2);
                } else if (img1 == null && img2 == null) {
                    return 0;
                }
                return img1.compareTo(img2);
            }
        });
    }

    @Override
    public int compareTo(DisplayImage other) {
        if (other == null)
            return -1;
        int zIndexDiff = this.zIndex - other.zIndex;
        if (zIndexDiff != 0)
            return zIndexDiff;

        int yDiff = this.roundY() - other.roundY();
        if (yDiff != 0)
            return yDiff;

        return this.getArea() - other.getArea();
    }

    protected BufferedImage getImage() {
        return img;
    }

    public BufferedImage getImageCopy() {
        return BufferedImageCreator.deepCopy(img);
    }

    public AABB getBounds() {
        return new AABB(bounds);
    }

    public double getX() {
        return bounds.getX();
    }

    public void setX(double x) {
        bounds.setX(x);
    }

    public double getY() {
        return bounds.getY();
    }

    public void setY(double y) {
        bounds.setY(y);
    }

    public int roundX() {
        return (int) Math.round(bounds.getX());
    }

    public int roundY() {
        return (int) Math.round(bounds.getY());
    }

    public double getX2() {
        return bounds.getX2();
    }

    public void setX2(double x2) {
        bounds.setX2(x2);
    }

    public double getY2() {
        return bounds.getY2();
    }

    public void setY2(double y2) {
        bounds.setY2(y2);
    }

    public int roundX2() {
        return (int) Math.round(bounds.getX2());
    }

    public int roundY2() {
        return (int) Math.round(bounds.getY2());
    }

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }

    public int getArea() {
        return getWidth() * getHeight();
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }

}