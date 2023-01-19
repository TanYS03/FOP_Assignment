module com.example.executegraph {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.executegraph to javafx.fxml;
    exports com.example.executegraph;
}