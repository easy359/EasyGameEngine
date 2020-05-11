package com.easy359.engine.game;

import com.easy359.engine.gfx.DisplayImage;

import java.util.List;

public interface GameState {

    public void update(double delta);

    public List<DisplayImage> getDisplayImages();

}
