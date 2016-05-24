package org.tomat.spbsu.sakod.figure;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainAddDel extends Application {
    public final static double WIDTH = 1000;
    public final static double HEIGHT = 700;

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

        root.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.isControlDown()) {
                    MyShape sh = new Strange();
                    sh.setOnMousePressed(e1 -> {
                        if (e1.getButton() == MouseButton.SECONDARY) {
                            root.getChildren().remove(sh);
                        }
                    });
                    sh.setLayoutX(e.getX());
                    sh.setLayoutY(e.getY());
                    root.getChildren().add(sh);
                } else {
                    Group sh = new Serpin();
                    sh.setOnMousePressed(e1 -> {
                        if (e1.getButton() == MouseButton.SECONDARY) {
                            root.getChildren().remove(sh);
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
}
