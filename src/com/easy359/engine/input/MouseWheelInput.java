package com.easy359.engine.input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelInput implements MouseWheelListener {

    private static MouseWheelInput instance;

    private static int scrollAmount;
    private static int lastScrollAmount;
    private static int lastScrollAmountTemp;

    private MouseWheelInput() {
        scrollAmount = 0;
        lastScrollAmount = 0;
        lastScrollAmountTemp = 0;
    }

    public static void create() {
        if (instance == null)
            instance = new MouseWheelInput();
    }

    public static MouseWheelInput getInstance() {
        create();
        return instance;
    }

    public static void update() {
        lastScrollAmount = lastScrollAmountTemp;
        lastScrollAmountTemp = 0;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrollAmount += e.getWheelRotation();
        lastScrollAmountTemp = e.getWheelRotation();
    }

    public static int getTotalMouseWheelScrollAmount() {
        return scrollAmount;
    }

    public static int getLastMouseWheelScrollAmount() {
        return lastScrollAmount;
    }
}
