package com.easy359.engine.menu;

import com.easy359.engine.gfx.DisplayImage;

public class Label implements MenuComponent {

    private DisplayImage img;

    public Label(DisplayImage img) {
        this.img = img;
    }

    @Override
    public void update(double delta) {
    }

    @Override
    public DisplayImage getDisplayImage() {
        return img;
    }

    public void setDisplayImage(DisplayImage img) {
        this.img = img;
    }

}
