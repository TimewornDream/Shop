module Shop {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens main to javafx.fxml, com.google.gson;
    exports main;
}