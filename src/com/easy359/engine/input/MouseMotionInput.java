package com.easy359.engine.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotionInput extends MouseMotionAdapter {

    private static MouseMotionInput instance;

    private static Point mouseLocation;
    private static Point mouseDragLocation;
    private static Point mouseDragStartLocation;
    private static boolean isMouseDragging;
    private static boolean isMouseDraggingTemp;

    private MouseMotionInput() {
	mouseLocation = new Point(-100, -100);
	mouseDragLocation = new Point(-100, -100);
	mouseDragStartLocation = new Point(-100, -100);
	isMouseDragging = false;
	isMouseDraggingTemp = false;
    }

    public static void create() {
	if (instance == null)
	    instance = new MouseMotionInput();
    }

    public static MouseMotionInput getInstance() {
	create();
	return instance;
    }

    public static void update() {
	if (isMouseDragging == false && isMouseDraggingTemp == true) {
	    mouseDragStartLocation = new Point((int) mouseLocation.getX(), (int) mouseLocation.getY());
	}
	isMouseDragging = isMouseDraggingTemp;
	isMouseDraggingTemp = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
	double height = e.getComponent().getHeight();
	mouseLocation = e.getPoint();
	mouseLocation.setLocation(mouseLocation.getX(), height - mouseLocation.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
	double height = e.getComponent().getHeight();
	mouseDragLocation = e.getPoint();
	mouseDragLocation.setLocation(mouseDragLocation.getX(), height - mouseDragLocation.getY());
	isMouseDraggingTemp = true;
    }

    public static Point getMouseLocation() {
	return mouseLocation;
    }

    public static Point getMouseDragLocation() {
	return mouseDragLocation;
    }

    public static Point getMouseDragStartLocation() {
	return mouseDragStartLocation;
    }

    public static boolean isMouseDragging() {
	return isMouseDragging;
    }
}
