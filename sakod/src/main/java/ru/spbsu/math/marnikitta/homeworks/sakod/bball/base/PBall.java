package ru.spbsu.math.marnikitta.homeworks.sakod.bball.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class PBall extends PObject{
    private double radius = 5;
    private double bounce = 1;


    public PBall(double x, double y, double r, double vx0, double vy0, double b){
        this.weight = new Weight(new Vector2D(x, y), new Vector2D(vx0, vy0));
        this.radius = r;
        this.bounce = b;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(6);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(weight.getPosition().getX() - radius, weight.getPosition().getY() - radius, radius * 2, radius * 2);
    }

    public void evalCollapse(PObject object) {
        if (object instanceof PPlane) {
            PPlane plane = (PPlane)object;
            if (plane.crossing(getPosition(), getPreviousPosition())) {
                stepBack();
                weight.velocity = plane.reflect(weight.velocity).scalarMultiply(bounce);
            }
        }
    }
}
