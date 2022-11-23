package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("conv.fxml")));
            Scene scene = new Scene (root);
            stage.setMinWidth(350);
            stage.setMinHeight(600);
            stage.setTitle("OCR Currency Converter");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }

    public static void main(String[] args) {
        launch(args);


    }
}