package com.easy359.engine.game;

import com.easy359.engine.input.UserInput;
import com.easy359.engine.time.Time;

public final class GameLoop implements Runnable {

    private Game game;
    private int tickRate;
    private Thread thread;
    private int fps;
    private int ups;
    private boolean isRunning;

    public GameLoop(Game game, int tickRate) {
	this.game = game;
	this.tickRate = tickRate;
	thread = null;
	fps = 0;
	isRunning = false;
    }

    public void start() {
	if (isRunning)
	    return;
	isRunning = true;
	thread = new Thread(this);
	thread.start();
    }

    public void stop() {
	isRunning = false;
    }

    @Override
    public void run() {
	double tickDelay = Time.secondsToNano(1.0 / tickRate);
	double now = Time.now();
	double lastTick = now;
	double nextTick = now;
	double nextSecond = now + Time.NANO_SECOND;
	double delta;

	int renders = 0;
	boolean shouldRender = false;
	int updates = 0;

	while (isRunning) {
	    while (Time.now() >= nextTick) {
		delta = Time.nanoToSeconds(Time.now() - lastTick);
		lastTick = Time.now();
		update((delta < 0.1) ? delta : 0.1);
		updates++;
		shouldRender = true;
		nextTick = lastTick + tickDelay;
	    }
	    if (shouldRender) {
		render();
		renders++;
		shouldRender = false;
	    } else {
		try {
		    Thread.sleep(1);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	    if (Time.now() >= nextSecond) {
		fps = renders;
		renders = 0;
		ups = updates;
		updates = 0;
		nextSecond += Time.NANO_SECOND;
	    }
	}
    }

    private void update(double delta) {
	UserInput.update();
	game.update(delta);
    }

    private void render() {
	game.render();
    }

    public int getFPS() {
	return fps;
    }

    public int getUPS() {
	return ups;
    }

}
