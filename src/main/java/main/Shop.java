package main;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Shop extends VBox {
    Shop() {
        super();

        // 页面上部，输入部分
        HBox inputBox = new HBox();
        inputBox.setMinSize(700, 70);

        // 名称输入文本框
        TextField nameInput = new TextField();
        nameInput.setPromptText("输入商品名称");
        nameInput.setStyle(
                "-fx-min-height: 50;" +
                        "-fx-min-width: 300;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: #000000;" +
                        "-fx-font-size: 16"
        );

        // 价格输入文本框
        TextField priceInput = new TextField();
        priceInput.setPromptText("输入商品价格（单位/元）");
        priceInput.setStyle(
                "-fx-min-height: 50;" +
                        "-fx-min-width: 150;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: #000000;" +
                        "-fx-font-size: 16"
        );

        // 添加商品按钮
        Button addButton = new Button("添加商品");
        addButton.setStyle(
                "-fx-min-height: 50;" +
                        "-fx-min-width: 50;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #4777d1;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: #000000;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-size: 16;" +
                        "-fx-cursor: hand"
        );

        // 调整inputBox子组件间距和位置
        inputBox.getChildren().addAll(nameInput, priceInput, addButton);
        inputBox.setSpacing(12);
        inputBox.setAlignment(Pos.TOP_CENTER);

        // 页面中部，商品展示部分
        VBox shopBox = new VBox();
        shopBox.setMinSize(800, 800);
        shopBox.setMaxSize(800, 800);
        shopBox.setStyle(
                "-fx-background-color: #ffffff;"
        );
        ScrollPane scrollPane = new ScrollPane(shopBox);
        scrollPane.setMinSize(640, 320);
        scrollPane.setMaxSize(640, 320);
        scrollPane.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-border-color: #000000;" +
                        "-fx-border-width: 2;" +
                        "-fx-padding: 3"
        );
        scrollPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // 各部分的提示文字
        Label label1 = new Label("商品上架");
        label1.setMinSize(640, 48);
        label1.setStyle(
                "-fx-font-size: 16;" +
                        "-fx-text-fill: #000000;"
        );
        Label label2 = new Label("商店");
        label2.setMinSize(640, 48);
        label2.setStyle(
                "-fx-font-size: 16;" +
                        "-fx-text-fill: #000000"
        );

        setAlignment(Pos.CENTER);
        getChildren().addAll(label1, inputBox, label2, scrollPane);
    }
}
