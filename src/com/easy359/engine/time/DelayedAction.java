package com.easy359.engine.time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelayedAction {

    private ActionListener listener;
    private double delay;
    private double delayStartTime;
    private double pauseStartTime;
    private boolean isPaused;

    public DelayedAction(ActionListener listener, double delay) {
        this.listener = listener;
        this.delay = delay;
        delayStartTime = Time.now();
        isPaused = false;
    }

    public void update(double delta) {
        if (isPaused)
            return;
        if (Time.now() - delayStartTime >= delay) {
            delayStartTime = Time.now();
            ActionEvent e = new ActionEvent(this, 0, "");
            listener.actionPerformed(e);
        }
    }

    public void reset() {
        delayStartTime = Time.now();
    }

    public void pause() {
        if (isPaused)
            return;
        isPaused = true;
        pauseStartTime = Time.now();

    }

    public void unpause() {
        if (!isPaused)
            return;
        isPaused = false;
        delayStartTime = (Time.now() - (pauseStartTime - delayStartTime));
    }

}
