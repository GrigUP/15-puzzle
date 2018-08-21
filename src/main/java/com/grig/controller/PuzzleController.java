package com.grig.controller;

import com.grig.model.PuzzleModel;
import com.grig.utills.Coordinates;
import com.grig.utills.Swapper;
import com.grig.view.PuzzleView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PuzzleController {
    private boolean isGameOver = false;
    private PuzzleModel model;
    private PuzzleView puzzleView;

    public PuzzleController(PuzzleModel model, PuzzleView puzzleView) {
        this.model = model;
        this.puzzleView = puzzleView;
    }

    public void updateScene() {
        puzzleView.update();
    }

    public void buttonAction(ActionEvent actionEvent) {
        if (!isGameOver) {
            Button actionButton = (Button) actionEvent.getSource();

            Coordinates coordinatesPushedButton = new Coordinates(GridPane.getColumnIndex(actionButton.getParent()), GridPane.getRowIndex(actionButton.getParent()));
            Coordinates coordinatesNull = model.getCoordinatesNull();

            if (isNeighborCoordinates(coordinatesPushedButton, coordinatesNull)) {
                Swapper swpr = new Swapper(model.getMassive());
                swpr.swap(coordinatesPushedButton, coordinatesNull);

                Coordinates tempCoordinates = coordinatesPushedButton;
                GridPane.setColumnIndex(actionButton.getParent(), coordinatesNull.getI());
                GridPane.setRowIndex(actionButton.getParent(), coordinatesNull.getJ());

                coordinatesNull.setI(tempCoordinates.getI());
                coordinatesNull.setJ(tempCoordinates.getJ());
            }
            isGameOver = model.isGameOver();
        }
        if (isGameOver) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Конец игры");
            alert.setHeaderText(null);
            alert.setContentText("Вы победили!");

            alert.showAndWait();
            model.shuffle(10);
            updateScene();
            isGameOver = false;
        }
    }

    private boolean isNeighborCoordinates(Coordinates coordinates1, Coordinates coordinates2) {
        int coordinateI1 = coordinates1.getI();
        int coordinateJ1 = coordinates1.getJ();

        int coordinateI2 = coordinates2.getI();
        int coordinateJ2 = coordinates2.getJ();

        return ((Math.abs(coordinateI1-coordinateI2) == 1 && (coordinateJ1-coordinateJ2 == 0))
                ^ (Math.abs(coordinates1.getJ()-coordinates2.getJ()) == 1 && (coordinateI1-coordinateI2 == 0)));
    }
}
