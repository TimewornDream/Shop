package main;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Shop extends VBox {
    Shop() {
        super();
        HBox inputBox = new HBox();
        inputBox.setMinSize(700, 100);
        inputBox.setStyle(
                "-fx-background-color: #000000"
        );

        VBox shopBox = new VBox();
        shopBox.setMinSize(700, 300);
        shopBox.setStyle(
                "-fx-background-color: #ffffff"
        );

        getChildren().addAll(inputBox, shopBox);
    }
}
