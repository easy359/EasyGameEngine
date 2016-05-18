package com.easy359.engine;

import java.util.ArrayList;
import java.util.List;

import com.easy359.engine.game.Game;
import com.easy359.engine.game.GameLoop;
import com.easy359.engine.gfx.Display;
import com.easy359.engine.gfx.DisplayImage;
import com.easy359.engine.input.UserInput;

public class EasyGame implements Game {

    private GameLoop gl;
    private Display display;
    private List<DisplayImage> imgs;

    public EasyGame() {
	gl = new GameLoop(this, 30);
	display = new Display("", 800, 600);
	UserInput.addTo(display);
	imgs = new ArrayList<DisplayImage>();
    }

    public void start() {
	gl.start();
    }

    @Override
    public void update(double delta) {
	display.setTitle(gl.getFPS() + "");
    }

    @Override
    public void render() {
	display.addImagesToBackBuffer(imgs);
	display.drawBackBuffer();
    }

    public static void main(String[] args) {
	EasyGame game = new EasyGame();
	game.start();
    }
}
