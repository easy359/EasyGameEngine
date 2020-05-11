package com.easy359.engine.gfx.translation;

import com.easy359.engine.gfx.DisplayImage;

import java.util.ArrayList;
import java.util.List;

public class MovementTranslator implements DisplayImageTranslator {

    private double dx;
    private double dy;

    public MovementTranslator(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void translate(DisplayImage img, double delta) {
        List<DisplayImage> imgs = new ArrayList<DisplayImage>(1);
        imgs.add(img);
        translate(imgs, delta);
    }

    @Override
    public void translate(List<DisplayImage> imgs, double delta) {
        for (DisplayImage img : imgs) {
            img.setX(img.getX() + (delta * dx));
            img.setY(img.getY() + (delta * dy));
        }
    }

    public void setDX(double dx) {
        this.dx = dx;
    }

    public void setDY(double dy) {
        this.dy = dy;
    }

}
