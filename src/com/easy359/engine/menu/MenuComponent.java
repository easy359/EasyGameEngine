package com.easy359.engine.menu;

import com.easy359.engine.gfx.DisplayImage;

public interface MenuComponent {

    public void update(double delta);

    public DisplayImage getDisplayImage();
}
