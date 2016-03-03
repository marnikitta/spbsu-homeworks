package org.tomat.spbsu.sakod.bball.base;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class PBall extends PObject{
    private double radius = 5;


    public PBall(double x, double y, double r, double vx0, double vy0){
        this.weight = new Weight(new Vector2D(x, y), new Vector2D(vx0, vy0));
        this.radius = r;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(6);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(weight.getPosition().getX() - radius, weight.getPosition().getY() - radius, radius * 2, radius * 2);
    }
}
