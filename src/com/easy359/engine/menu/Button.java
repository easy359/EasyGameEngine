package com.easy359.engine.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.easy359.engine.gfx.BufferedImageTransformer;
import com.easy359.engine.gfx.DisplayImage;
import com.easy359.engine.input.UserInput;
import com.easy359.engine.physics.AABB;

public class Button implements MenuComponent {

    private AABB bounds;
    private DisplayImage img;
    private DisplayImage highlight;
    private List<ActionListener> listeners;

    public Button(AABB bounds, DisplayImage img) {
	this.bounds = bounds;
	this.img = img;
	BufferedImage transformed = BufferedImageTransformer.highlight(img.getImageCopy(), 0x33AAAAAA);
	highlight = new DisplayImage(transformed, img.getX(), img.getY(), img.getZIndex());
	listeners = new ArrayList<ActionListener>();
    }

    @Override
    public void update(double delta) {
	if (UserInput.isLeftClickJustPressed()) {
	    Point mouseLoc = UserInput.getMouseLocation();
	    if (bounds.contains(mouseLoc)) {
		ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
		for (ActionListener listener : listeners) {
		    listener.actionPerformed(evt);
		}
	    }
	}
    }

    public void addActionListener(ActionListener listener) {
	listeners.add(listener);
    }

    public void addActionListeners(List<ActionListener> listeners) {
	for (ActionListener listener : listeners) {
	    addActionListener(listener);
	}
    }

    @Override
    public DisplayImage getDisplayImage() {
	if (bounds.contains(UserInput.getMouseLocation())) {
	    return highlight;
	}
	return img;
    }

}
