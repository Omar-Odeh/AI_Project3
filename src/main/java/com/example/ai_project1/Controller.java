package com.example.ai_project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private ComboBox from, to;

    @FXML
    private Button BFS_button;

    @FXML
    private Button DFS_button;

    @FXML
    private Button aStar_button;

    @FXML
    private Text path;

    @FXML
    private RadioButton rb1, rb2;

    @FXML
    void onAStar(ActionEvent event) {
        path.setText(Application.aStar(from.getValue().toString(), to.getValue().toString(), Application.g));
    }

    @FXML
    void onBFS(ActionEvent event) {
        path.setText(Application.breadthFirstSearch(Application.g, from.getValue().toString(), to.getValue().toString()));
    }

    @FXML
    void onDFS(ActionEvent event) {
        path.setText(Application.depthFirstSearch(Application.g, from.getValue().toString(), to.getValue().toString()));
    }

    @FXML
    void getAStar(ActionEvent event) {
        if(rb1.isSelected()) {
            path.setText("walking");
        }
        else if(rb2.isSelected()) {
            path.setText("driving");
        }
    }
}
