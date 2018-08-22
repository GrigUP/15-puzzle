package com.grig.view;

import com.grig.model.PuzzleModel;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PuzzleView {
    private PuzzleModel model;
    private Stage stage;

    public PuzzleView(PuzzleModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }

    public void update() {
        int[][] massive = model.getMassive();
        GridPane gridPane = (GridPane) stage.getScene().getRoot();
        int index = -1;
        for (Node node: gridPane.getChildren()) {
            index++;
            Button button = (Button) ((AnchorPane)node).getChildren().get(0);
            button.setText(massive[index/model.getN()][index%model.getN()] + "");
        }
    }

    public Stage getStage() {
        return stage;
    }
}
