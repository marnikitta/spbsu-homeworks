package ru.spbsu.math.marnikitta.homeworks.sakod.figure;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Border extends MyShape {
    public Border(MyShape shape) {
        double width = shape.prefWidth(0);
        double height = shape.prefHeight(0);
        Rectangle rect = new Rectangle(-width / 2, -height / 2, width, height);
        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(4);
        rect.setStroke(Color.DARKBLUE);
        this.getChildren().add(rect);
        this.getChildren().addAll(shape);
    }
}
