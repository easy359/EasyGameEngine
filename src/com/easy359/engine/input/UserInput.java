package com.easy359.engine.input;

import com.easy359.engine.gfx.Display;

import java.awt.*;

public class UserInput {

    private UserInput() {
    }

    public static void create() {
        KeyboardInput.create();
        MouseInput.create();
        MouseMotionInput.create();
        MouseWheelInput.create();
    }

    public static void update() {
        KeyboardInput.update();
        MouseInput.update();
        MouseMotionInput.update();
        MouseWheelInput.update();
    }

    public static void addTo(Display display) {
        display.addKeyListener(KeyboardInput.getInstance());
        display.addMouseListener(MouseInput.getInstance());
        display.addMouseMotionListener(MouseMotionInput.getInstance());
        display.addMouseWheelListener(MouseWheelInput.getInstance());
    }

    public static boolean isUpArrowPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.UP);
    }

    public static boolean isUpArrowJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.UP);
    }

    public static boolean isLeftArrowPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.LEFT);
    }

    public static boolean isLeftArrowJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.LEFT);
    }

    public static boolean isDownArrowPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.DOWN);
    }

    public static boolean isDownArrowJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.DOWN);
    }

    public static boolean isRightArrowPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.RIGHT);
    }

    public static boolean isRightArrowJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.RIGHT);
    }

    public static boolean isWPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.W);
    }

    public static boolean isWJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.W);
    }

    public static boolean isAPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.A);
    }

    public static boolean isAJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.A);
    }

    public static boolean isSPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.S);
    }

    public static boolean isSJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.S);
    }

    public static boolean isDPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.D);
    }

    public static boolean isDJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.D);
    }

    public static boolean isSpacePressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.SPACE);
    }

    public static boolean isSpaceJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.SPACE);
    }

    public static boolean isEnterPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.ENTER);
    }

    public static boolean isEnterJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.ENTER);
    }

    public static boolean isTabPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.TAB);
    }

    public static boolean isTabJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.TAB);
    }

    public static boolean isEscapePressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.ESCAPE);
    }

    public static boolean isEscapeJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.ESCAPE);
    }

    public static boolean isControlPressed() {
        return KeyboardInput.isKeyPressed(KeyboardInput.CONTROL);
    }

    public static boolean isControlJustPressed() {
        return KeyboardInput.isKeyJustPressed(KeyboardInput.CONTROL);
    }

    public static boolean isRightClickPressed() {
        return MouseInput.isButtonPressed(MouseInput.RIGHT_CLICK);
    }

    public static boolean isRightClickJustPressed() {
        return MouseInput.isButtonJustPressed(MouseInput.RIGHT_CLICK);
    }

    public static boolean isLeftClickPressed() {
        return MouseInput.isButtonPressed(MouseInput.LEFT_CLICK);
    }

    public static boolean isLeftClickJustPressed() {
        return MouseInput.isButtonJustPressed(MouseInput.LEFT_CLICK);
    }

    public static Point getMouseLocation() {
        return MouseMotionInput.getMouseLocation();
    }

    public static int getTotalMouseWheelScrollAmount() {
        return MouseWheelInput.getTotalMouseWheelScrollAmount();
    }

    public static int getLastMouseWheelScrollAmount() {
        return MouseWheelInput.getLastMouseWheelScrollAmount();
    }
}
