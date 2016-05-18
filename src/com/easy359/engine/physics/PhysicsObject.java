package com.easy359.engine.physics;

public class PhysicsObject implements Bounded {

    private AABB bounds;
    private Motion2d motion;

    public PhysicsObject() {
	bounds = new AABB();
	motion = new Motion2d();
    }

    public PhysicsObject(AABB bounds) {
	this.bounds = bounds;
	motion = new Motion2d();
    }

    public PhysicsObject(AABB bounds, Motion2d motion) {
	this.bounds = bounds;
	this.motion = motion;
    }

    public void update(double delta) {
	motion.update(delta);
    }

    public double timeOfCollision(double delta, PhysicsObject other) {
	double time = -1;

	double xStart = this.bounds.getCenterX();
	double yStart = other.bounds.getCenterY();

	double xVel = this.motion.getVelocity().getX();
	double yVel = this.motion.getVelocity().getY();
	return time;
    }

    @Override
    public AABB getBounds() {
	return bounds;
    }

    public Motion2d getMotion2d() {
	return motion;
    }

}
