package org.tomat.spbsu.sakod.bball;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import org.tomat.spbsu.sakod.bball.base.SimulationSystem;


public class JumpingBall extends Application {
    public final static double G = 9.8;
    public final static int H = 40;
    public final static double V_0 = 5;
    public final static double BOUNCE = 100;
    public final static double RADIUS = H;
    ConstantVelocity constantVelocity = new ConstantVelocity();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setResizable(false);
        Group root = new Group();
        Canvas canvas = new Canvas(400, 300);
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
                if (now - then > 1E7) {
                    double dt = (now - then) / 1E9;
                    constantVelocity.operate(dt);
                    gc.clearRect(0, 0, 400, 300);
                    constantVelocity.draw(gc);
                    then = now;
                }
            }
        };
        animationTimer.start();
    }
}

