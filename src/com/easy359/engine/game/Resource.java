package com.easy359.engine.game;

import com.easy359.engine.gfx.BufferedImageCreator;

import java.awt.image.BufferedImage;

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
                    "res/EasyIcon.png", 0, 0, 20, 20);
        }
    }

}
