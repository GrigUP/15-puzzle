package com.grig.main;

import com.grig.controller.PuzzleController;
import com.grig.model.PuzzleModel;
import com.grig.view.PuzzleView;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

        PuzzleModel puzzleModel = new PuzzleModel(4);
        PuzzleView puzzleView = new PuzzleView(puzzleModel, primaryStage);
        PuzzleController puzzleController = new PuzzleController(puzzleModel, puzzleView);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainWindow.fxml"));
        fxmlLoader.setController(puzzleController);

        Scene scene = new Scene((Parent)fxmlLoader.load(), 410, 490);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));

        primaryStage.initStyle(StageStyle.UNDECORATED);

        puzzleController.updateScene();
        puzzleController.initializeKeyboardEvent();
        puzzleController.initializeMouseEvent();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
