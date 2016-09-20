package ru.spbsu.math.marnikitta.homeworks.sakod.exam;

import java.io.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class ProblemC extends Application {
    public final static double WIDTH = 1000;
    public final static double HEIGHT = 700;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public Pane root;
    public double xSt, ySt;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);

        root = new Pane();
        root.setOnMousePressed(e -> {
            root.getChildren().clear();
            xSt = e.getX();
            ySt = e.getY();
        });
        root.setOnMouseReleased(e -> {
            Polyline p = getLine(e.getX() - xSt, e.getY() - ySt);
            p.setLayoutX(xSt);
            p.setLayoutY(ySt);
            root.getChildren().add(p);
        });

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    public Polyline getLine(double x, double y) {
        double[] points = new double[]{0,0,x,y,0,y,x,0,0,0};
        Polyline line = new Polyline(points);
        line.setStroke(Color.DARKORANGE);
        line.setStrokeWidth(2);
        return line;
    }
}
