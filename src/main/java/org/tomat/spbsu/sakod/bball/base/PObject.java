package org.tomat.spbsu.sakod.bball.base;

import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public abstract class PObject {
    protected Weight weight = new Weight();
    protected boolean fixed = false;

    public void simulate(double time) {
        weight.simulate(time);
    }

    public void initForce() {
        weight.initForce();
    }

    public void addForce(Vector2D f) {
        weight.addForce(f);
    }

    public double getWeight() {
        return weight.getWeight();
    }

    public void addG(double g) {
        this.addForce(new Vector2D(0, g * getWeight()));
    }

    public abstract void draw(GraphicsContext gc);
}
