package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class Goods extends HBox {
    private String name;
    private double price;
    private int amount;
    Goods(String name, double price, int amount, int type) {
        // 初始化数据域
        this.name = name;
        this.price = price;
        this.amount = amount;

        // 构建样式
        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.CENTER_LEFT);
        Label[] labels = new Label[3];
        labels[0] = new Label(name);
        labels[1] = new Label(String.format("%.2f", this.price));
        labels[2] = new Label(String.format("%d", this.amount));
        for (Label label: labels) {
            label.setStyle(
                    "-fx-font-size: 16;" +
                            "-fx-text-fill: #000000;" +
                            "-fx-min-width: 120;"
            );
            labelBox.getChildren().add(label);
            label.setAlignment(Pos.CENTER_LEFT);
        }

        // 按钮
        if (type == 0) {
            GoodsButton purchaseButton = new GoodsButton(0);
            GoodsButton modifyButton = new GoodsButton(1);
            GoodsButton deleteButton = new GoodsButton(2);
            getChildren().addAll(labelBox, purchaseButton, modifyButton, deleteButton);
        } else {
            GoodsButton modifyButton = new GoodsButton(3);
            GoodsButton deleteButton = new GoodsButton(2);
            getChildren().addAll(labelBox, modifyButton, deleteButton);
        }

        // hang over 样式
        setOnMouseEntered(e->{
            setStyle(
                    "-fx-background-color: #e7e7e7"
            );
        });

        setOnMouseExited(e->{
            setStyle(
                    "-fx-background-color: #ffffff"
            );
        });

        setMinHeight(50);
        setPadding(new Insets(5, 0, 5, 5));
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(20);
    }
}
