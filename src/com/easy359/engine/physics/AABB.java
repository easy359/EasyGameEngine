package com.easy359.engine.physics;

import java.awt.geom.Point2D;
import java.util.Objects;

public final class AABB implements Bounded {

    private double x;
    private double y;
    private double width;
    private double height;

    public AABB() {
	x = 0;
	y = 0;
	width = 0;
	height = 0;
    }

    public AABB(AABB other) {
	this(other.x, other.y, other.width, other.height);
    }

    public AABB(double x, double y, double width, double height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }

    @Override
    public AABB getBounds() {
	return this;
    }

    public boolean intersects(AABB other) {
	if (other == null)
	    return false;
	return intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
    }

    public boolean intersects(double x, double y, double width, double height) {
	if (isEmpty() || width <= 0 || height <= 0)
	    return false;
	double x2 = x + width;
	double y2 = y + height;
	return (this.getX2() >= x) && (this.x <= x2) && (this.getY2() >= y) && (this.y <= y2);
    }

    public boolean contains(Point2D point) {
	if (point == null)
	    return false;
	return contains(point.getX(), point.getY());
    }

    public boolean contains(double x, double y) {
	if (isEmpty())
	    return false;
	return (x >= this.x) && (x <= this.getX2()) && (y >= this.y) && (y <= this.getY2());
    }

    public double distanceBetweenCenters(AABB other) {
	if (this.isEmpty() || other.isEmpty())
	    return -1;
	double xDis = this.getCenterX() - other.getCenterX();
	double yDis = this.getCenterY() - other.getCenterY();
	return Math.sqrt((xDis * xDis) + (yDis * yDis));
    }

    public double distanceBetweenCenters(double x, double y, double width, double height) {
	return distanceBetweenCenters(new AABB(x, y, width, height));
    }

    public double destanceToEdge(double angle) {
	// TODO: IMPLEMENT
	return 0;
    }

    public boolean isEmpty() {
	return (width <= 0 || height <= 0);
    }

    public double getX() {
	return x;
    }

    public void setX(double x) {
	this.x = x;
    }

    public double getY() {
	return y;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double getX2() {
	return x + width;
    }

    public void setX2(double x2) {
	x = x2 - width;
    }

    public double getY2() {
	return y + height;
    }

    public void setY2(double y2) {
	y = y2 - height;
    }

    public double getCenterX() {
	return x + halfWidth();
    }

    public void setCenterX(double centerX) {
	x = centerX - halfWidth();
    }

    public double getCenterY() {
	return y + halfHeight();
    }

    public void setCenterY(double centerY) {
	y = centerY - halfHeight();
    }

    public double getWidth() {
	return width;
    }

    public void setWidth(double width) {
	this.width = width;
    }

    public double getHeight() {
	return height;
    }

    public void setHeight(double height) {
	this.height = height;
    }

    public double halfWidth() {
	return width / 2;
    }

    public double halfHeight() {
	return height / 2;
    }

    public double getArea() {
	return width * height;
    }

    @Override
    public int hashCode() {
	return Objects.hash(x, y, width, height);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof AABB) {
	    AABB other = (AABB) obj;
	    return (this.x == other.x && this.y == other.y && this.width == other.width && this.height == other.height);
	}
	return false;
    }

    @Override
    public String toString() {
	return "com.easy359.physics.AABB@" + Integer.toHexString(hashCode()) + "[x=" + x + ",y=" + y + ",width=" + width
		+ ",height=" + height + "]";
    }

    public static void main(String[] args) {
	AABB one = new AABB(0, 0, 10, 10);
	AABB two = new AABB(5, 0, 10, 10);
	System.out.println(two.distanceBetweenCenters(one));
    }

}