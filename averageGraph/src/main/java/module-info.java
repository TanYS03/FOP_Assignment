module com.example.averagegraph {
    requires javafx.controls;
    requires javafx.fxml;
    requires ini4j;


    opens com.example.averagegraph to javafx.fxml;
    exports com.example.averagegraph;
}