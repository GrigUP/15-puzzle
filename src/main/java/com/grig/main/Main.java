package com.grig.main;

import com.grig.controller.PuzzleController;
import com.grig.model.PuzzleModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    int width = 60;
    int height = 60;

    public void start(Stage primaryStage) throws Exception {

        PuzzleModel puzzleModel = new PuzzleModel(5);
        puzzleModel.shuffle(3);
        puzzleModel.print();

        PuzzleController puzzleController = new PuzzleController();
        GridPane gridPane = new GridPane();
        int[][] massive = puzzleModel.getMassive();
        int n = puzzleModel.getN();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n-1 && j == n-1) {
                    break;
                }

                Button newButton = new Button();
                newButton.setText(String.valueOf(massive[i][j]));
                newButton.setMaxSize(width, height);
                newButton.setMinSize(width, height);
                gridPane.add(newButton, j, i);
            }
        }
        Scene scene = new Scene(gridPane, width*n, height*n);

        primaryStage.setScene(scene);
        primaryStage.setTitle("15-puzzle");
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
