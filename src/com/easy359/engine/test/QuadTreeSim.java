package com.easy359.engine.test;

import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.easy359.engine.game.Game;
import com.easy359.engine.game.GameLoop;
import com.easy359.engine.gfx.BufferedImageCreator;
import com.easy359.engine.gfx.Display;
import com.easy359.engine.gfx.DisplayImage;
import com.easy359.engine.input.UserInput;
import com.easy359.engine.physics.AABB;
import com.easy359.engine.physics.partioning.QuadTree;

public class QuadTreeSim implements Game {

    private GameLoop gl;
    private Display display;
    private QuadTree<AABB> qt;
    private List<DisplayImage> imgs;

    public QuadTreeSim() {
	gl = new GameLoop(this, 30);
	display = new Display("", 800, 600);
	UserInput.addTo(display);
	qt = new QuadTree<AABB>(new AABB(0, 0, 800, 600), 2, 10);
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
	    qt.insert(bounds);
	}
	display.setTitle(gl.getFPS() + "");
    }

    @Override
    public void render() {
	display.addImagesToBackBuffer(qt.createDisplayImages());
	display.addImagesToBackBuffer(imgs);
	display.drawBackBuffer();
    }

    public static void main(String[] args) {
	QuadTreeSim sim = new QuadTreeSim();
	sim.start();
    }

}
