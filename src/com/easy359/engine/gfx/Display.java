package com.easy359.engine.gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.easy359.engine.game.Resource;

public class Display {

    private Canvas canvas;
    private JFrame frame;
    private BufferStrategy bs;
    private BufferedImage screen;
    private List<DisplayImage> buffer;

    public Display(String title, int width, int height) {
	canvas = new Canvas();
	canvas.setSize(new Dimension(width, height));
	canvas.setIgnoreRepaint(true);

	frame = new JFrame(title);
	Resource.FrameIcon icon = new Resource.FrameIcon();
	frame.setIconImage(icon.getFrameIcon());
	frame.setResizable(false);
	frame.add(canvas);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

	canvas.requestFocus();
	canvas.createBufferStrategy(2);
	bs = canvas.getBufferStrategy();

	screen = new BufferedImage(width, height, Transparency.TRANSLUCENT);

	buffer = new ArrayList<DisplayImage>();
    }

    public void addImageToBackBuffer(DisplayImage img) {
	if (img != null)
	    buffer.add(img);
    }

    public void addImagesToBackBuffer(List<DisplayImage> imgs) {
	if (imgs == null)
	    return;
	for (DisplayImage img : imgs) {
	    addImageToBackBuffer(img);
	}
    }

    public void drawBackBuffer() {
	DisplayImage.sort(buffer);
	Graphics2D g2 = (Graphics2D) screen.getGraphics();
	g2.setBackground(Color.black);
	g2.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	for (DisplayImage img : buffer) {
	    g2.drawImage(img.getImage(), img.roundX(), (canvas.getWidth() - img.roundY()) - img.getHeight(), null);
	}
	g2.dispose();
	do {
	    do {
		g2 = (Graphics2D) bs.getDrawGraphics();
		g2.drawImage(screen, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g2.dispose();
	    } while (bs.contentsRestored());
	    bs.show();
	} while (bs.contentsLost());
    }

    public void clearBackBuffer() {
	buffer.clear();
    }

    public void addKeyListener(KeyListener key) {
	canvas.addKeyListener(key);
    }

    public void addMouseListener(MouseListener mouse) {
	canvas.addMouseListener(mouse);
    }

    public void addMouseMotionListener(MouseMotionListener motion) {
	canvas.addMouseMotionListener(motion);
    }

    public void addMouseWheelListener(MouseWheelListener wheel) {
	canvas.addMouseWheelListener(wheel);
    }

    public void setFrameIcon(Image img) {
	frame.setIconImage(img);
    }

    public void setFrameIcons(List<Image> imgs) {
	frame.setIconImages(imgs);
    }

    public String getTitle() {
	return frame.getTitle();
    }

    public void setTitle(String title) {
	frame.setTitle(title);
    }

}
