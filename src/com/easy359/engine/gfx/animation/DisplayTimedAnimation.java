package com.easy359.engine.gfx.animation;

import com.easy359.engine.time.Time;

import java.util.ArrayList;
import java.util.List;

public class DisplayTimedAnimation {

    private List<DisplayTimedImage> imgs;
    private int curImg;
    private double frameStartTime;
    private double pauseStartTime;
    private boolean isPaused;

    public DisplayTimedAnimation(List<DisplayTimedImage> imgs) {
        this.imgs = new ArrayList<DisplayTimedImage>(imgs.size());
        this.imgs.addAll(imgs);
        curImg = 0;
        frameStartTime = Time.now();
        pauseStartTime = 0;
        isPaused = false;
    }

    public void reset() {
        curImg = 0;
        frameStartTime = Time.now();
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
        frameStartTime = (Time.now() - (pauseStartTime - frameStartTime));
    }

    public DisplayTimedImage getCurrentImage() {
        if (isPaused)
            return imgs.get(curImg);
        double imgDelay = Time.secondsToNano(imgs.get(curImg).getDelay());
        double timeDelta = Time.now() - frameStartTime;
        if (timeDelta >= imgDelay) {
            if (++curImg >= imgs.size())
                curImg = 0;
            double frameTimeOverflow = (timeDelta - imgDelay);
            frameStartTime = Time.now() - frameTimeOverflow;
        }
        return imgs.get(curImg);
    }

}
