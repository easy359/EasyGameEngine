package com.easy359.engine.game;

import java.awt.image.BufferedImage;

import com.easy359.engine.gfx.BufferedImageCreator;

public final class Resource {

    private Resource() {
    }

    public static class FrameIcon {
	private static BufferedImage icon;

	public BufferedImage getFrameIcon() {
	    if (icon == null)
		initResources();
	    return BufferedImageCreator.deepCopy(icon);
	}

	private static void initResources() {
	    icon = BufferedImageCreator.createImageFromFilePath(
		    "c:/users/ian/workspace/java projects/EasyGameEngine/res/EasyIcon.png", 0, 0, 20, 20);
	}
    }

}
