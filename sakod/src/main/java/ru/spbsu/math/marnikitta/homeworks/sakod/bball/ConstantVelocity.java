package ru.spbsu.math.marnikitta.homeworks.sakod.bball;

import ru.spbsu.math.marnikitta.homeworks.sakod.bball.base.PBall;
import ru.spbsu.math.marnikitta.homeworks.sakod.bball.base.PObject;
import ru.spbsu.math.marnikitta.homeworks.sakod.bball.base.PPlane;
import ru.spbsu.math.marnikitta.homeworks.sakod.bball.base.SimulationSystem;

public class ConstantVelocity extends SimulationSystem {
    public double g;
    public double bounce;

    public PBall ball;
    public PBall ball1;

    public ConstantVelocity(double g, double v_0, double bounce, double radius, double width, double height) {
        this.g = g;

        ball = new PBall(0 + radius, 0 + radius, radius, v_0, 0, bounce);
        ball1 = new PBall(0 + radius, 0 + radius, radius, v_0 / 2, 0, bounce);
        this.add(ball);
        this.add(ball1);
        this.add(new PPlane(0, 0, width - radius, 0));
        this.add(new PPlane(0, 0, 0, height - radius));
        this.add(new PPlane(0, height - radius, width - radius, height - radius));
        this.add(new PPlane(width, 0, width - radius, height - radius));
    }

    @Override
    protected void solve() {
        ball.addG(g);
        ball1.addG(g);
        for (PObject pObject : this) {
            ball.evalCollapse(pObject);
            ball1.evalCollapse(pObject);
        }
    }
}
