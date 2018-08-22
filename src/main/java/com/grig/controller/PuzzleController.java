package com.grig.controller;

import com.grig.model.PuzzleModel;
import com.grig.utills.Coordinates;
import com.grig.utills.Swapper;
import com.grig.view.PuzzleView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class PuzzleController {
    private boolean isGameOver = false;
    private PuzzleModel model;
    private PuzzleView puzzleView;

    @FXML
    GridPane gridPane;

    public PuzzleController(PuzzleModel model, PuzzleView puzzleView) {
        this.model = model;
        this.puzzleView = puzzleView;
    }

    public void initializeEvent() {
        Scene scene = puzzleView.getStage().getScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!isGameOver) {
                    Coordinates coordinatesNull = model.getCoordinatesNull();
                    Coordinates coordinatesMovedButton = null;
                    switch (event.getCode()) {
                        case RIGHT:
                            if (coordinatesNull.getI() - 1 >= 0) {
                                coordinatesMovedButton = new Coordinates(coordinatesNull.getI() - 1, coordinatesNull.getJ());
                            }
                            break;
                        case LEFT:
                            if (coordinatesNull.getI() + 1 < model.getN()) {
                                coordinatesMovedButton = new Coordinates(coordinatesNull.getI() + 1, coordinatesNull.getJ());
                            }
                            break;
                        case DOWN:
                            if (coordinatesNull.getJ() - 1 >= 0) {
                                coordinatesMovedButton = new Coordinates(coordinatesNull.getI(), coordinatesNull.getJ() - 1);
                            }
                            break;
                        case UP:
                            if (coordinatesNull.getJ() + 1 < model.getN()) {
                                coordinatesMovedButton = new Coordinates(coordinatesNull.getI(), coordinatesNull.getJ() + 1);
                            }
                            break;
                        case ESCAPE:
                            puzzleView.getStage().close();
                            return;
                    }

                    if (coordinatesMovedButton == null) return;

                    Node movedNode = getNodeFromGridPaneByIndex(gridPane, coordinatesMovedButton);
                    Swapper swpr = new Swapper(model.getMassive());
                    swpr.swap(coordinatesMovedButton, coordinatesNull);

                    Coordinates tempCoordinates = coordinatesMovedButton;

                    GridPane.setColumnIndex(movedNode, coordinatesNull.getI());
                    GridPane.setRowIndex(movedNode, coordinatesNull.getJ());

                    coordinatesNull.setI(tempCoordinates.getI());
                    coordinatesNull.setJ(tempCoordinates.getJ());
                    isGameOver = model.isGameOver();
                }
                if (isGameOver) gameOverDialog();
            }
        });

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
                isGameOver = model.isGameOver();
            }

        }
        if (isGameOver) gameOverDialog();
    }

    private boolean isNeighborCoordinates(Coordinates coordinates1, Coordinates coordinates2) {
        int coordinateI1 = coordinates1.getI();
        int coordinateJ1 = coordinates1.getJ();

        int coordinateI2 = coordinates2.getI();
        int coordinateJ2 = coordinates2.getJ();

        return ((Math.abs(coordinateI1-coordinateI2) == 1 && (coordinateJ1-coordinateJ2 == 0))
                ^ (Math.abs(coordinates1.getJ()-coordinates2.getJ()) == 1 && (coordinateI1-coordinateI2 == 0)));
    }

    private Node getNodeFromGridPaneByIndex(GridPane gridPane, Coordinates coordinates) {
        for (Node node:gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == coordinates.getJ() && GridPane.getColumnIndex(node) == coordinates.getI()) return node;
        }
        return null;
    }

    private void gameOverDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("GameOver");
        alert.setHeaderText(null);
        alert.setContentText("You win!");

        alert.showAndWait();
        model.shuffle(10);
        updateScene();
        isGameOver = false;
    }
}
