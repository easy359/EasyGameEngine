package com.easy359.engine.time;

/**
 * Created by Ian on 1/20/2016.
 */
public class Time {

    public static final double NANO_SECOND = 1_000_000_000;

    public static double now() {
        return System.nanoTime();
    }

    public static double nanoToSeconds(double nano) {
        return nano / NANO_SECOND;
    }

    public static double secondsToNano(double seconds) {
        return seconds * NANO_SECOND;
    }
}
