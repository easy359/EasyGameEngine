package com.easy359.engine.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseInput extends MouseAdapter {

    public static final int LEFT_CLICK = MouseEvent.BUTTON1;
    public static final int SCROLL_CLICK = MouseEvent.BUTTON2;
    public static final int RIGHT_CLICK = MouseEvent.BUTTON3;
    public static final int SCROLL_WHEEL = MouseEvent.MOUSE_WHEEL;

    private static MouseInput instance;

    private static ArrayList<Integer> pressedButtons;
    private static ArrayList<Integer> justPressedButtons;
    private static ArrayList<Integer> afterPressedButtons;

    private MouseInput() {
        pressedButtons = new ArrayList<Integer>();
        justPressedButtons = new ArrayList<Integer>();
        afterPressedButtons = new ArrayList<Integer>();
    }

    public static void create() {
        if (instance == null)
            instance = new MouseInput();
    }

    public static MouseInput getInstance() {
        create();
        return instance;
    }

    public static void update() {
        synchronized (pressedButtons) {
            for (int i = 0; i < pressedButtons.size(); i++) {
                if (!justPressedButtons.contains(pressedButtons.get(i))
                        && !afterPressedButtons.contains(pressedButtons.get(i))) {
                    justPressedButtons.add(pressedButtons.get(i));
                } else {
                    justPressedButtons.remove(Integer.valueOf(pressedButtons.get(i)));
                    afterPressedButtons.add(pressedButtons.get(i));
                }
            }
            justPressedButtons.retainAll(pressedButtons);
            afterPressedButtons.retainAll(pressedButtons);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        synchronized (pressedButtons) {
            if (!pressedButtons.contains(Integer.valueOf(e.getButton()))) {
                pressedButtons.add(e.getButton());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        synchronized (pressedButtons) {
            pressedButtons.remove(Integer.valueOf(e.getButton()));
        }
    }

    public static boolean isButtonPressed(int button) {
        return pressedButtons.contains(button);
    }

    public static boolean isButtonJustPressed(int button) {
        return justPressedButtons.contains(button);
    }
}
