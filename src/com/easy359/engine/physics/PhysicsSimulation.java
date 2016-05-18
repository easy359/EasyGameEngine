package com.easy359.engine.physics;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulation {

    private List<PhysicsObject> objects;

    public PhysicsSimulation() {
	objects = new ArrayList<PhysicsObject>();
    }

    public PhysicsSimulation(List<PhysicsObject> objects) {
	this.objects = objects;
    }

    public void update(double delta) {
	for (PhysicsObject obj : objects) {
	    obj.update(delta);
	}
    }

    public void run() {

    }

    private void findFirstCollision() {

    }

}
