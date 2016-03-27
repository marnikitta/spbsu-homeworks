package org.tomat.spbsu.sakod.figure;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.*;


public class MainAddDel extends Application {
    public final static double WIDTH = 1000;
    public final static double HEIGHT = 700;

    public GraphicsContext gc;
    public ArrayList<Shape> shapes = new ArrayList<>();

    public Pane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setResizable(false);

        root = new Pane();

        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    Shape sh = createShape();
                    sh.setOnMousePressed(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            if (e.getButton() == MouseButton.SECONDARY) {
                                root.getChildren().remove(sh);
                            }
                        }
                    });
                    sh.setLayoutX(e.getX());
                    sh.setLayoutY(e.getY());
                    root.getChildren().add(sh);
                }
            }
        });

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }


    public Shape createShape() {
        double r = 40;
        int n = 6;
        double[] point = new double[2*n];
        int j = 0;
        for (int i = 0; i < n; ++i) {
            point[j++] = r * Math.cos(2.0 * Math.PI  * i/ n);
            point[j++] = (r * Math.sin(2.0 * Math.PI  * i/ n));
        }
        return new Polygon(point);
    }
}


