package com.easy359.engine.menu;

import com.easy359.engine.gfx.DisplayImage;
import com.easy359.engine.gfx.translation.DisplayImageTranslator;

public class MovingLabel extends Label {

    private DisplayImageTranslator[] translators;

    public MovingLabel(DisplayImage img, DisplayImageTranslator... translators) {
	super(img);
	this.translators = translators;
    }

    @Override
    public void update(double delta) {
	for (DisplayImageTranslator translator : translators) {
	    translator.translate(getDisplayImage(), delta);
	}
    }

}
