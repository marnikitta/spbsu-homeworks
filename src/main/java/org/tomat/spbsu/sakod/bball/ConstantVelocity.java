package org.tomat.spbsu.sakod.bball;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.tomat.spbsu.sakod.bball.base.PBall;
import org.tomat.spbsu.sakod.bball.base.PObject;
import org.tomat.spbsu.sakod.bball.base.SimulationSystem;

public class ConstantVelocity extends SimulationSystem{
    public ConstantVelocity() {
        this.add(new PBall(500, 0, 50, 0, 0));
    }
    @Override
    protected void solve() {
        for (PObject ob : this) {
            ob.addG(200);
            System.out.println(ob.getPosition().getY());
            if (Math.abs(ob.getPosition().getY() - 600) < 30 && ob.weight.getVelocity().getY() > 0) {
                ob.reverse(0.9);
            }
        }
    }
}
