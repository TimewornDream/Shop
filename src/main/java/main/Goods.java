package main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Goods extends HBox {
    private String name;
    private double price;
    private int amount;
    Goods(String name, double price, int amount) {
        // 初始化数据域
        this.name = name;
        this.price = price;
        this.amount = amount;

        // 构建样式
        Label[] labels = new Label[3];
        labels[0] = new Label(name);
        labels[1] = new Label(String.format("%.2f", this.price));
        labels[2] = new Label(String.format("%d", this.amount));
        for (Label label: labels) {
            label.setStyle(
                    "-fx-font-size: 16;" +
                            "-fx-text-fill: #000000;" +
                            "-fx-min-width: 120"
            );
            getChildren().add(label);
        }
        setMinHeight(50);
        setPadding(new Insets(10, 0, 10, 5));
    }
}
