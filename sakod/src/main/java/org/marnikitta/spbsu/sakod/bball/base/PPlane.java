package org.marnikitta.spbsu.sakod.bball.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class PPlane extends PObject {
    private final Vector2D position;
    private final Vector2D direction;
    private final Vector2D norm;

    public PPlane(double x1, double y1, double x2, double y2) {
        position = new Vector2D(x1, y1);
        Vector2D position2 = new Vector2D(x2, y2);
        direction = position2.add(position.negate());
        norm = new Vector2D(direction.getY(), direction.negate().getX());

        fixed = true;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(2);
        gc.setStroke(Color.DARKGREEN);

        gc.moveTo(position.getX(), position.getY());
        Vector2D to = position.add(direction);
        gc.lineTo(to.getX(), to.getY());
    }

    public Vector2D reflect(Vector2D vector) {
        double scalar = vector.dotProduct(norm) / norm.dotProduct(norm);
        Vector2D norm2 = norm.scalarMultiply(2 * scalar);
        return vector.subtract(norm2);
    }

    public double distance(Vector2D point) {
        double d = point.subtract(position).crossProduct(position, position.add(direction));
        return Math.abs(d / direction.getNorm());
    }
    public boolean crossing(Vector2D position1, Vector2D position2) {
        Vector2D position222 = position.add(direction);
        double v1 = position1.crossProduct(position, position222);
        double v2 = position2.crossProduct(position, position222);
        return v1 * v2 < 0;
    }
}
