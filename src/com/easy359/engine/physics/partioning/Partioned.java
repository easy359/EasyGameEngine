package com.easy359.engine.physics.partioning;

import com.easy359.engine.physics.AABB;
import com.easy359.engine.physics.Bounded;

import java.util.List;

public interface Partioned<T extends Bounded> {

    public void insert(T t);

    public void insertAll(List<T> ts);

    public List<T> getContainedObjects(AABB bounds);

    public void clear();

}
