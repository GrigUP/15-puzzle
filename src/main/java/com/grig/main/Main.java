package com.grig.main;

import com.grig.controller.PuzzleController;
import com.grig.model.PuzzleModel;
import com.grig.view.PuzzleView;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

        PuzzleModel puzzleModel = new PuzzleModel(4);
        PuzzleView puzzleView = new PuzzleView(puzzleModel, primaryStage);
        puzzleModel.shuffle(10);
        PuzzleController puzzleController = new PuzzleController(puzzleModel, puzzleView);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainWindow.fxml"));
        fxmlLoader.setController(puzzleController);

        Scene scene = new Scene((Parent)fxmlLoader.load(), 400, 400);
        scene.getStylesheets().addAll("/buttonStyle.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("15-puzzle");
        primaryStage.setResizable(true);

        puzzleController.updateScene();
        puzzleController.initializeEvent();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
