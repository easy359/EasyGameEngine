package com.easy359.engine.physics.partioning;

import com.easy359.engine.gfx.BufferedImageCreator;
import com.easy359.engine.gfx.DisplayImage;
import com.easy359.engine.physics.AABB;
import com.easy359.engine.physics.Bounded;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class QuadTree<T extends Bounded> implements Partioned<T> {

    private Node root;
    private int size;
    private int levels;

    public QuadTree(AABB bounds, int size, int levels) {
        root = new Node(bounds, 1);
        this.size = size;
        this.levels = levels;
    }

    @Override
    public void insert(T t) {
        root.insert(t);
    }

    @Override
    public void insertAll(List<T> ts) {
        root.insertAll(ts);
    }

    @Override
    public List<T> getContainedObjects(AABB bounds) {
        return root.getContainedObjects(bounds);
    }

    @Override
    public void clear() {
        root.clear();
    }

    public List<DisplayImage> createDisplayImages() {
        return root.createDisplayImages();
    }

    private class Node {

        private Node NW;
        private Node NE;
        private Node SW;
        private Node SE;
        private List<T> objs;
        private AABB bounds;
        private int level;

        private Node(AABB bounds, int level) {
            NW = null;
            NE = null;
            SW = null;
            SE = null;
            objs = new ArrayList<T>(size);
            this.bounds = bounds;
            this.level = level;
        }

        private void initNodes() {
            double leftX = bounds.getX();
            double middleX = bounds.getCenterX();
            double rightX = bounds.getX2();
            double topY = bounds.getY();
            double middleY = bounds.getCenterY();
            double bottomY = bounds.getY2();
            AABB nwBounds = new AABB(leftX, topY, middleX - leftX, middleY - topY);
            AABB neBounds = new AABB(middleX, topY, rightX - middleX, middleY - topY);
            AABB swBounds = new AABB(leftX, middleY, middleX - leftX, bottomY - middleY);
            AABB seBounds = new AABB(middleX, middleY, rightX - middleX, bottomY - middleY);
            NW = new Node(nwBounds, level + 1);
            NE = new Node(neBounds, level + 1);
            SW = new Node(swBounds, level + 1);
            SE = new Node(seBounds, level + 1);
        }

        private void insert(T t) {
            AABB tBounds = t.getBounds();
            if (tBounds.intersects(bounds)) {
                if (!isFull() || isOnFinalLevel())
                    objs.add(t);
                else {
                    if (!hasChilds()) {
                        initNodes();
                        insertAll(objs);
                        objs.clear();
                    }
                    if (tBounds.intersects(NW.bounds))
                        NW.insert(t);
                    if (tBounds.intersects(NE.bounds))
                        NE.insert(t);
                    if (tBounds.intersects(SW.bounds))
                        SW.insert(t);
                    if (tBounds.intersects(SE.bounds))
                        SE.insert(t);
                }
            }
        }

        private void insertAll(List<T> ts) {
            for (T t : ts) {
                insert(t);
            }
        }

        private List<T> getContainedObjects(AABB bounds) {
            List<T> contained = new ArrayList<T>();
            if (bounds.intersects(this.bounds)) {
                if (hasChilds()) {
                    contained.addAll(NW.getContainedObjects(bounds));
                    contained.addAll(NE.getContainedObjects(bounds));
                    contained.addAll(SW.getContainedObjects(bounds));
                    contained.addAll(SE.getContainedObjects(bounds));
                } else {
                    contained.addAll(objs);
                }
            }
            return contained;
        }

        private void clear() {
            objs.clear();
            if (hasChilds()) {
                NW.clear();
                NE.clear();
                SW.clear();
                SE.clear();
            }
        }

        private boolean hasChilds() {
            return NW != null;
        }

        private boolean isFull() {
            return (hasChilds()) ? true : objs.size() >= size;
        }

        private boolean isOnFinalLevel() {
            return level >= levels;
        }

        private List<DisplayImage> createDisplayImages() {
            List<DisplayImage> imgs = new ArrayList<DisplayImage>();
            if (NW != null) {
                imgs.addAll(NW.createDisplayImages());
                imgs.addAll(NE.createDisplayImages());
                imgs.addAll(SW.createDisplayImages());
                imgs.addAll(SE.createDisplayImages());
            }
            BufferedImage img = BufferedImageCreator.createOutlinedRectangularImage(0xFF, 0xFFFFFF,
                    (int) bounds.getWidth(), (int) bounds.getHeight(), Transparency.OPAQUE);
            imgs.add(new DisplayImage(img, (int) bounds.getX(), (int) bounds.getY(), level));
            return imgs;
        }
    }

}
