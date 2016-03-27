package org.tomat.spbsu.sakod.figure;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class Serpin extends MyShape {
    private double depth = 5;
    private double a = 200;
    private ArrayList<Line> lines = new ArrayList<>();

    public Serpin() {
        super();
        double m = a * Math.sqrt(3) / 3;
        double h = a * Math.sqrt(3) / 2;
        makeTrig(0, 0, 0, -m, -a / 2, h - m, a / 2, h - m);
        makeTrig(0, 1, 0, -m, -a / 2, h - m, a / 2, h - m);
        makeTrig(0, 2, 0, -m, -a / 2, h - m, a / 2, h - m);

        Polygon poly = new Polygon(0, -m, -a / 2, h - m, a / 2, h - m);
        poly.setFill(Color.TRANSPARENT);
        poly.setStrokeWidth(1);
        poly.setStroke(Color.BLACK);
        this.getChildren().add(poly);
        this.getChildren().addAll(lines);
    }

    public void makeTrig(int depth, int position, double topX, double topY, double leftX, double leftY, double rightX, double rightY) {
        if (depth == this.depth) {
            return;
        }
        if (position == 0) {
            leftX = (leftX + topX) / 2;
            leftY = (leftY + topY) / 2;
            rightX = (rightX + topX) / 2;
            rightY = (rightY + topY) / 2;
            Line l = new Line(leftX, leftY, rightX, rightY);
            l.setStroke(Color.BLACK);
            l.setStrokeWidth(1);
            lines.add(l);
            makeTrig(depth + 1, 0, topX, topY, leftX, leftY, rightX, rightY);
            makeTrig(depth + 1, 1, topX, topY, leftX, leftY, rightX, rightY);
            makeTrig(depth + 1, 2, topX, topY, leftX, leftY, rightX, rightY);
        } else if (position == 1) {
            topX = (leftX + topX) / 2;
            topY = (leftY + topY) / 2;
            rightX = (rightX + leftX) / 2;
            rightY = (rightY + leftY) / 2;

            Line l = new Line(topX, topY, rightX, rightY);
            l.setStroke(Color.BLACK);
            l.setStrokeWidth(1);
            lines.add(l);


            makeTrig(depth + 1, 0, topX, topY, leftX, leftY, rightX, rightY);
            makeTrig(depth + 1, 1, topX, topY, leftX, leftY, rightX, rightY);
            makeTrig(depth + 1, 2, topX, topY, leftX, leftY, rightX, rightY);
        } else if (position == 2) {
            topX = (rightX + topX) / 2;
            topY = (rightY + topY) / 2;
            leftX = (rightX + leftX) / 2;
            leftY = (rightY + leftY) / 2;

            Line l = new Line(topX, topY, leftX, leftY);
            l.setStroke(Color.BLACK);
            l.setStrokeWidth(1);
            lines.add(l);

            makeTrig(depth + 1, 0, topX, topY, leftX, leftY, rightX, rightY);
            makeTrig(depth + 1, 1, topX, topY, leftX, leftY, rightX, rightY);
            makeTrig(depth + 1, 2, topX, topY, leftX, leftY, rightX, rightY);
        }
    }
}

