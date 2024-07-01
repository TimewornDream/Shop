package main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Shop extends VBox {
    Shop() {
        super();
        HBox inputBox = new HBox();
        inputBox.setMinSize(700, 100);
//        inputBox.setStyle(
////                "-fx-background-color: #000000"
//        );

        VBox shopBox = new VBox();
        shopBox.setMinSize(700, 300);
        shopBox.setStyle(
                "-fx-background-color: #ffffff"
        );

        // 名称输入文本框
        TextField nameInput = new TextField();
        Font nameInputFont = Font.font("System");
        nameInput.setPromptText("输入商品名称");
        nameInput.setStyle(
                "-fx-min-height: 50;" +
                        "-fx-min-width: 300;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #f5f4f5;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: #000000;" +
                        "-fx-font-size: 16"
        );
        TextField priceInput = new TextField();

        // 价格输入文本框
        priceInput.setPromptText("输入商品价格（单位/元）");
        priceInput.setStyle(
                "-fx-min-height: 50;" +
                        "-fx-min-width: 150;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #f5f4f5;" +
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
                        "-fx-font-size: 16"
        );

        // 调整inputBox子组件间距和位置
        inputBox.getChildren().addAll(nameInput, priceInput, addButton);
        inputBox.setSpacing(12);
        inputBox.setAlignment(Pos.CENTER);

        getChildren().addAll(inputBox, shopBox);
    }
}
