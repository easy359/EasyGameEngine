package com.easy359.engine.gfx.translation;

import com.easy359.engine.gfx.DisplayImage;

import java.util.List;

public interface DisplayImageTranslator {

    public void translate(DisplayImage img, double delta);

    public void translate(List<DisplayImage> imgs, double delta);

}
