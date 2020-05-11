package com.easy359.engine.physics;

import java.awt.geom.Point2D;
import java.util.Objects;

public class Vector2d {

    private double x;
    private double y;

    public Vector2d() {
        x = 0;
        y = 0;
    }

    public Vector2d(Vector2d other) {
        this(other.x, other.y);
    }

    public Vector2d(double angle) {
        this(1, 0); // make sure length is one
        setAngle(angle);
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D getPoint2D() {
        return new Point2D.Double(x, y);
    }

    public double calcDotProduct(Vector2d other) {
        return (this.x * other.x) + (this.y * other.y);
    }

    public double calcCrossProduct(Vector2d other) {
        return (this.x * other.y) - (this.y * other.x);
    }

    public double calcAngleBetween(Vector2d other) {
        return this.getAngle() - other.getAngle();
    }

    public void add(Vector2d other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
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

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getLength() {
        return Math.sqrt((x * x) + (y * y));
    }

    public void setLength(double length) {
        double angle = getAngle(); // needed because angle changes after setting
        // x
        x = Math.cos(angle) * length;
        y = Math.sin(angle) * length;
    }

    public double getAngle() {
        double angle = Math.atan2(y, x);
        return (angle >= 0) ? angle : angle + (2 * Math.PI);
    }

    public void setAngle(double angle) {
        double length = getLength(); // needed because length changes after
        // setting x
        x = Math.cos(angle) * length;
        y = Math.sin(angle) * length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2d) {
            Vector2d other = (Vector2d) obj;
            return (this.x == other.x && this.y == other.y);
        }
        return false;
    }

    @Override
    public String toString() {
        return "com.easy359.engine.physics.Vector2d@" + Integer.toHexString(hashCode()) + "[x=" + x + ",y=" + y
                + ",length=" + getLength() + ",angle=" + getAngle() + "]";
    }

}
