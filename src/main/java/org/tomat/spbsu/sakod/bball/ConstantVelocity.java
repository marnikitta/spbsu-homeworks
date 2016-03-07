package org.tomat.spbsu.sakod.bball;

import org.tomat.spbsu.sakod.bball.base.PBall;
import org.tomat.spbsu.sakod.bball.base.PObject;
import org.tomat.spbsu.sakod.bball.base.PPlane;
import org.tomat.spbsu.sakod.bball.base.SimulationSystem;

public class ConstantVelocity extends SimulationSystem {
    public double g;
    public double bounce;

    public PBall ball;

    public ConstantVelocity(double g, double v_0, double bounce, double radius, double width, double height) {
        this.g = g;

        ball = new PBall(0 + radius, 0 + radius, radius, v_0, 0, bounce);
        this.add(ball);
        this.add(new PPlane(0, 0, width - radius, 0));
        this.add(new PPlane(0, 0, 0, height - radius));
        this.add(new PPlane(0, height - radius, width - radius, height - radius));
        this.add(new PPlane(width, 0, width - radius, height - radius));
    }

    @Override
    protected void solve() {
        System.out.println(ball.getPosition());
        ball.addG(g);
        for (PObject pObject : this) {
            ball.evalCollapse(pObject);
        }
    }
}
