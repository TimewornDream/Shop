package main;

import javafx.geometry.Insets;
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
        Label labelTop = new Label("购物车");
        labelTop.setMinSize(640, 48);
        labelTop.setStyle(
                "-fx-font-size: 16;" +
                        "-fx-text-fill: #000000;"
        );

        // 购物车
        VBox shoppingCarBox = new VBox();
        shoppingCarBox.setMinSize(640, 310);
        shoppingCarBox.setStyle(
                "-fx-background-color: #ffffff"
        );
        HBox labelBox = new HBox();
        labelBox.setPadding(new Insets(5, 0, 0, 5));
        Label[] labels = new Label[3];
        labels[0] = new Label("名称");
        labels[1] = new Label("价格（元）");
        labels[2] = new Label("数量（个）");

        for (Label label: labels) {
            label.setStyle(
                    "-fx-font-size: 16;" +
                            "-fx-text-fill: #000000;" +
                            "-fx-min-width: 120"
            );
            labelBox.getChildren().add(label);
        }

        shoppingCarBox.getChildren().add(labelBox);

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

        getChildren().addAll(new Label(), labelTop, scrollPane);
        setAlignment(Pos.TOP_CENTER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}