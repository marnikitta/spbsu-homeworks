package org.marnikitta.spbsu.sakod.bball.base;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Weight {
    private Vector2D position = new Vector2D(10, 10);
    private Vector2D force = new Vector2D(0, 5000);
    Vector2D velocity = new Vector2D(50, 0);
    private Vector2D previousPosition = new Vector2D(10, 10);

    private double weight = 100;

    public Weight() {
    }

    public double getWeight() {
        return weight;
    }

    public Vector2D getForce() {
        return force;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getPreviousPosition() {
        return previousPosition;
    }
    public Weight(Vector2D position) {
        this.position = position;
    }

    public void stepBack() {
        position = previousPosition;
    }

    public Weight(Vector2D position, Vector2D velocity) {
        this.position = position;
        this.previousPosition = position;
        this.velocity = velocity;
    }

    public void initForce() {
        this.force = new Vector2D(0, 0);
    }

    public void addForce(Vector2D force) {
        this.force = this.force.add(force);
    }

    public Vector2D getPosition() {
        return position;
    }

    public synchronized void simulate(double dt) {
        velocity = velocity.add(force.scalarMultiply(dt / weight));
        previousPosition = position;
        position = position.add(velocity.scalarMultiply(dt));
    }

    public void reverse(double coef) {
        this.velocity = this.velocity.scalarMultiply(-1 * coef);
    }
}
