package com.easy359.engine.game;

import java.util.List;

import com.easy359.engine.gfx.DisplayImage;

public interface GameState {

    public void update(double delta);

    public List<DisplayImage> getDisplayImages();

}
