package main;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class ShoppingCar extends VBox {
    ShoppingCar(){
        super();

        // 购物车顶部文字
        Label label = new Label("购物车");
        label.setMinSize(640, 48);
        label.setStyle(
                "-fx-font-size: 16;" +
                        "-fx-text-fill: #000000;"
        );

        // 购物车
        VBox shoppingCarBox = new VBox();
        shoppingCarBox.setMinSize(800, 800);
        shoppingCarBox.setMaxSize(800, 800);
        shoppingCarBox.setStyle(
                "-fx-background-color: #ffffff"
        );
        ScrollPane scrollPane = new ScrollPane(shoppingCarBox);
        scrollPane.setMinSize(640, 320);
        scrollPane.setMaxSize(640, 320);
        scrollPane.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-border-color: #000000;" +
                        "-fx-border-width: 2;" +
                        "-fx-padding: 3"
        );
        scrollPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        getChildren().addAll(new Label(), label, scrollPane);
        setAlignment(Pos.TOP_CENTER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}