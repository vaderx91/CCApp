module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.charm.glisten;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires tess4j;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}