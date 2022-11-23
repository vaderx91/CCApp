package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class ConvController implements Initializable {

    @FXML
    private ToggleButton convertButton;

    @FXML
    private ComboBox drpFrom;

    @FXML
    private ComboBox drpSource;

    @FXML
    private ComboBox drpTo;

    @FXML
    private TextField inpAmount;

    @FXML
    private ToggleButton langButton;

    @FXML
    private ToggleButton menuButton;

    @FXML
    private ToggleButton ocrButton;

    @FXML
    private ToggleButton offlineButton;

    @FXML
    private Label outPLabel;

    CurrencyService currencyService;
    List<Currency> listCurrency;

    @Override
    public void initialize(URL uri, ResourceBundle resourceBundle) {
        try {
            String url = "https://api.apilayer.com/fixer";
            String apiKey = "5tPCKZJ0rlxRIMEjXY8V8ywYUvaY3nVP";
            ApiConnection apiConnection = new ApiConnection(url, apiKey);
            JsonConversion jsonConversion = new JsonConversion();
            Converter con = new Converter(apiConnection, jsonConversion);
            this.currencyService = new CurrencyService(con);

            List<Currency> listCurrency = currencyService.getAllCurrencies();
            List<String> listSymbols = currencyService.getAllSymbols(listCurrency);

            this.drpFrom.getItems().addAll(listSymbols);
            this.drpTo.getItems().addAll(listSymbols);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void eventConvert(ActionEvent event) {
        try {
            String from = this.drpFrom.getSelectionModel().getSelectedItem().toString();
            String to = this.drpTo.getSelectionModel().getSelectedItem().toString();
            Double amount = Double.parseDouble(this.inpAmount.getText());
            Double result = this.currencyService.conv(from, to, amount);
            String resultF = String.format("%.2f",result);
            outPLabel.setText(resultF + " " + to);
            outPLabel.setVisible(true);
        } catch (Exception var6) {
            this.alertMessage();
            System.err.println(var6.getMessage());
        }

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("men.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLang(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lang.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOcr(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ocr.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void alertMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid data input");
        alert.setContentText("Please fill all the fields");
        alert.showAndWait();
    }

}




