package org.tomat.spbsu.sakod.bball;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.tomat.spbsu.sakod.bball.base.PBall;
import org.tomat.spbsu.sakod.bball.base.PObject;
import org.tomat.spbsu.sakod.bball.base.SimulationSystem;

public class ConstantVelocity extends SimulationSystem{
    public ConstantVelocity() {
        this.add(new PBall(10, 10, 5, 50, 0));
    }
    @Override
    protected void solve() {
        for (PObject ob : this) {
            ob.addG(50000);
        }
    }
}
