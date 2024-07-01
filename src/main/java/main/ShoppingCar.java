package main;

import javafx.scene.layout.HBox;

public class ShoppingCar extends HBox {
    ShoppingCar(){
        super();
        HBox shoppingCarBox = new HBox();
        shoppingCarBox.setMaxSize(700, 300);
        shoppingCarBox.setStyle(
                "-fx-background-color: #3f3f3f"
        );
        getChildren().addAll(shoppingCarBox);
    }
}