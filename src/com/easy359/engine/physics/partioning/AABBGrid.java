package com.easy359.engine.physics.partioning;

import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.easy359.engine.gfx.BufferedImageCreator;
import com.easy359.engine.gfx.DisplayImage;
import com.easy359.engine.physics.AABB;
import com.easy359.engine.physics.Bounded;

public class AABBGrid<T extends Bounded> implements Partioned<T> {

    private List<Node> nodes;
    private AABB bounds;
    private int rows;
    private int cols;

    public AABBGrid(AABB bounds, int rows, int cols) {
	nodes = new ArrayList<Node>(rows * cols);
	this.bounds = bounds;
	this.rows = rows;
	this.cols = cols;
	initNodes();
    }

    private void initNodes() {
	double nodeWidth = bounds.getWidth() / cols;
	double nodeHeight = bounds.getHeight() / rows;
	for (int x = 0; x < cols; x++) {
	    for (int y = 0; y < rows; y++) {
		AABB nodeBounds = new AABB(x * nodeWidth, y * nodeHeight, nodeWidth, nodeHeight);
		Node node = new Node(nodeBounds);
		nodes.add(node);
	    }
	}
    }

    @Override
    public void insert(T t) {
	AABB tBounds = t.getBounds();
	if (!tBounds.intersects(bounds))
	    return;
	for (Node node : nodes) {
	    if (tBounds.intersects(node.bounds)) {
		node.insert(t);
	    }
	}
    }

    @Override
    public void insertAll(List<T> ts) {
	for (T t : ts) {
	    insert(t);
	}
    }

    @Override
    public List<T> getContainedObjects(AABB bounds) {
	List<T> ts = new ArrayList<T>();
	for (Node node : nodes) {
	    if (bounds.intersects(node.bounds))
		ts.addAll(node.getContainedObjects());
	}
	return ts;
    }

    @Override
    public void clear() {
	for (Node node : nodes) {
	    node.clear();
	}
    }

    public List<DisplayImage> createDisplayImages() {
	List<DisplayImage> imgs = new ArrayList<DisplayImage>(rows * cols);
	for (Node node : nodes) {
	    imgs.add(node.createDisplayImage());
	}
	return imgs;
    }

    private class Node {

	private List<T> objs;
	private AABB bounds;

	private Node(AABB bounds) {
	    objs = new ArrayList<T>();
	    this.bounds = bounds;
	}

	private void insert(T t) {
	    objs.add(t);
	}

	private List<T> getContainedObjects() {
	    return objs;
	}

	private void clear() {
	    objs.clear();
	}

	private DisplayImage createDisplayImage() {
	    BufferedImage img = BufferedImageCreator.createOutlinedRectangularImage(0xFF, 0xFFFFFF,
		    (int) bounds.getWidth(), (int) bounds.getHeight(), Transparency.OPAQUE);
	    return new DisplayImage(img, (int) bounds.getX(), (int) bounds.getY(), 1);
	}
    }
}
