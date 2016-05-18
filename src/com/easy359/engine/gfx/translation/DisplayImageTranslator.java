package com.easy359.engine.gfx.translation;

import java.util.List;

import com.easy359.engine.gfx.DisplayImage;

public interface DisplayImageTranslator {

    public void translate(DisplayImage img, double delta);

    public void translate(List<DisplayImage> imgs, double delta);

}
