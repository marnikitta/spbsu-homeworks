package org.tomat.spbsu.sakod;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/***
 * ����:  ����� N
 * ����� � ��� ���� ���������� ������ ��������, ������ � �������� �����.
 * �� ����� ������� �� ��� ������ ����� N.
 * ��� ���� �� �����, ����� ����� ������� �� ������ ������� � ������� �����, ��� ��� ������� ���������.
 * <p>
 * �����: ������� ���� ������ �������� ��������� ����� ������?
 * <p>
 * �������������� �������: N ����� ���� ���������� �������, ����� ������ ���� ���������� ��� �������� � ������� ��.
 * ���� ��������� ���-�� ����� �����������.
 */

public class Trains extends Application {
    public Pane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox hb = new HBox();
        hb.setSpacing(10);

        final TextField name = new TextField();
        name.setPromptText("Enter the length of the train");
        hb.getChildren().add(name);

        Button submit = new Button("Submit");
        hb.getChildren().add(submit);


        submit.setOnAction(e -> {
            int n = Integer.parseInt(name.getText());
            Pane root = new Pane(new Label("Answer: " + count(n)));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });

        root = hb;
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Map<Integer, Long> m = new HashMap<>();

    public long count(int n) {
        if (n == 1) return 3;
        if (n == 2) return 7;

        if (!m.containsKey(n)) {
            m.put(n, 2 * count(n - 1) + count(n - 2));
        }

        return m.get(n);
    }

}

