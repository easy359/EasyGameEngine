package com.easy359.engine.test;

import com.easy359.engine.game.Game;
import com.easy359.engine.game.GameLoop;
import com.easy359.engine.gfx.BufferedImageCreator;
import com.easy359.engine.gfx.Display;
import com.easy359.engine.gfx.DisplayImage;
import com.easy359.engine.input.UserInput;
import com.easy359.engine.physics.AABB;
import com.easy359.engine.physics.partioning.AABBGrid;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AABBGridSim implements Game {

    private GameLoop gl;
    private Display display;
    private AABBGrid<AABB> grid;
    private List<DisplayImage> imgs;

    public AABBGridSim() {
        gl = new GameLoop(this, 30);
        display = new Display("", 800, 600);
        UserInput.addTo(display);
        grid = new AABBGrid<AABB>(new AABB(0, 0, 800, 600), 3, 3);
        imgs = new ArrayList<DisplayImage>();
    }

    public void start() {
        gl.start();
    }

    @Override
    public void update(double detla) {
        if (UserInput.isLeftClickJustPressed()) {
            Point mouseLoc = UserInput.getMouseLocation();
            AABB bounds = new AABB(mouseLoc.getX() - 5, mouseLoc.getY() - 5, 5, 5);
            BufferedImage img = BufferedImageCreator.createRectangularImage(0xFF0000, (int) bounds.getWidth(),
                    (int) bounds.getHeight(), Transparency.OPAQUE);
            imgs.add(new DisplayImage(img, (int) bounds.getX(), (int) bounds.getY(), 100));
            grid.insert(bounds);
        }
        display.setTitle(gl.getFPS() + "");
    }

    @Override
    public void render() {
        display.addImagesToBackBuffer(grid.createDisplayImages());
        display.addImagesToBackBuffer(imgs);
        display.drawBackBuffer();
    }

    public static void main(String[] arg) {
        AABBGridSim sim = new AABBGridSim();
        sim.start();
    }

}
