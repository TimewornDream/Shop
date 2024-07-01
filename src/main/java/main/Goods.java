package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        Button purchaseButton = new Button("购买");
        setButtonStyle(purchaseButton);
        Button modifyButton = new Button("修改");
        setButtonStyle(modifyButton);
        Button deleteButton = new Button("删除");
        setButtonStyle(deleteButton);

        setMinHeight(50);
        setPadding(new Insets(10, 0, 0, 5));
        getChildren().addAll(labelBox, purchaseButton, modifyButton, deleteButton);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(20);
    }
    private static void setButtonStyle (Button button) {
        button.setStyle(
                "-fx-min-height: 24;" +
                        "-fx-border-radius: 8;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #4777d1;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: #000000;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-size: 16;" +
                        "-fx-cursor: hand"
        );
    }
}
