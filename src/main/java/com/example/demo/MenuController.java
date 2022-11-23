package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {

    @FXML
    private ToggleButton aboutButton;

    @FXML
    private ToggleButton backMButton;


    @FXML
    private ToggleButton premButton;

    @FXML
    private ToggleButton rateButton;

    @FXML
    private ToggleButton themeButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToConvM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("conv.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
