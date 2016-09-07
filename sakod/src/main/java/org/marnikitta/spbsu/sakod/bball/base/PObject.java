package org.marnikitta.spbsu.sakod.bball.base;

import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public abstract class PObject {
    protected Weight weight = new Weight();
    protected boolean fixed;

    public void simulate(double time) {
        weight.simulate(time);
    }

    public void initForce() {
        weight.initForce();
    }

    public void addForce(Vector2D f) {
        weight.addForce(f);
    }

    public Vector2D getVelocity() {
        return weight.getVelocity();
    }

    public Vector2D getPreviousPosition() {
        return weight.getPreviousPosition();
    }

    public void stepBack() {
        weight.stepBack();
    }


    public boolean isFixed() {
        return fixed;
    }

    public double getWeight() {
        return weight.getWeight();
    }

    public Vector2D getPosition() {
        return weight.getPosition();
    }

    public void addG(double g) {
        this.addForce(new Vector2D(0, g * getWeight()));
    }

    public void reverse(double c) {
        weight.reverse(c);
    }

    public abstract void draw(GraphicsContext gc);
}
