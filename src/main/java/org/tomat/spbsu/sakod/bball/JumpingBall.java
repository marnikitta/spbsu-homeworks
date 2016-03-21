package org.tomat.spbsu.sakod.bball;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class JumpingBall extends Application {
    public final static double G = 250;
    public final static double V_0 = 800;
    public final static double BOUNCE = 0.80;
    public final static double RADIUS = 50;
    public final static double WIDTH = 1000;
    public final static double HEIGHT = 700;
    public final static double RESTART = 5;

    ConstantVelocity constantVelocity = new ConstantVelocity(G, V_0, BOUNCE, RADIUS, WIDTH, HEIGHT);


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setResizable(false);
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        doIt(canvas.getGraphicsContext2D());
    }

    long then = System.nanoTime();

    public void doIt(GraphicsContext gc) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - then > 1E6) {
                    double dt = (now - then) / 1E9;

                    constantVelocity.operate(dt);
                    gc.clearRect(0, 0, WIDTH, HEIGHT);
                    constantVelocity.draw(gc);

                    then = now;
                }
            }
        };
        animationTimer.start();
    }
}

