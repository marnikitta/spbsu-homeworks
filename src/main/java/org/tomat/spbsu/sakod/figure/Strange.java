package org.tomat.spbsu.sakod.figure;


import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Strange extends MyShape {
    private double r = 80;
    private int n = 12;

    public Strange() {
        super();
        double[] point = new double[2 * n];
        int j = 0;
        for (int i = 0; i < n; ++i) {
            point[j++] = r * Math.cos(2.0 * Math.PI * i / n);
            point[j++] = (r * Math.sin(2.0 * Math.PI * i / n));
        }
        Shape poly = new Polygon(point);
        poly.setFill(Color.TRANSPARENT);
        poly.setStrokeWidth(1);
        this.getChildren().add(poly);
        for (int i = 0; i < point.length / 2; ++i) {
            for (int k = i + 1; k < point.length / 2; ++k) {
                Line l = new Line(point[2 * i], point[2 * i + 1], point[2 * k], point[2 * k + 1]);
                l.setStrokeWidth(1);
                l.setStroke(Color.BLACK);
                this.getChildren().add(l);
            }
        }
    }
}
