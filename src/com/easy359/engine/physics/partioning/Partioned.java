package com.easy359.engine.physics.partioning;

import java.util.List;

import com.easy359.engine.physics.AABB;
import com.easy359.engine.physics.Bounded;

public interface Partioned<T extends Bounded> {

    public void insert(T t);

    public void insertAll(List<T> ts);

    public List<T> getContainedObjects(AABB bounds);

    public void clear();

}
