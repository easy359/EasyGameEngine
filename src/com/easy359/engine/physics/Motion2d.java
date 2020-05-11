package com.easy359.engine.physics;

public final class Motion2d {

    private final Vector2d position;
    private final Vector2d velocity;
    private double maxVel;
    private final Vector2d acceleration;

    public Motion2d() {
        position = new Vector2d();
        velocity = new Vector2d();
        maxVel = Double.MAX_VALUE;
        acceleration = new Vector2d();
    }

    public Motion2d(Motion2d other) {
        position = new Vector2d(other.position);
        velocity = new Vector2d(other.velocity);
        maxVel = other.maxVel;
        acceleration = new Vector2d(other.acceleration);
    }

    public Motion2d(double xPos, double yPos) {
        this();
        position.setX(xPos);
        position.setY(yPos);
    }

    public Motion2d(double xPos, double yPos, double maxVelocity) {
        this(xPos, yPos);
        maxVel = maxVelocity;
    }

    public void update(double delta) {
        double x = acceleration.getX() * delta;
        double y = acceleration.getY() * delta;
        velocity.translate(x, y);
        checkVelocity();
        x = velocity.getX() * delta;
        y = velocity.getY() * delta;
        position.translate(x, y);
    }

    private void checkVelocity() {
        if (velocity.getX() > maxVel)
            velocity.setX(maxVel);
        else if (velocity.getX() < -maxVel)
            velocity.setX(-maxVel);
        if (velocity.getY() > maxVel)
            velocity.setY(maxVel);
        else if (velocity.getY() < -maxVel)
            velocity.setY(-maxVel);
    }

    public Vector2d getPosition() {
        return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public double getMaxVelocity() {
        return maxVel;
    }

    public void setMaxVelocity(double maxVelocity) {
        maxVel = maxVelocity;
    }

    public Vector2d getAcceleration() {
        return acceleration;
    }

    @Override
    public String toString() {
        return "com.easy359.engine.physics.Motion2d@" + Integer.toHexString(hashCode()) + "[\npostion=" + position
                + ",\nveloctiy=" + velocity + ",\nacceleration=" + acceleration + "]";
    }

}
