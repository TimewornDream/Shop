package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class Goods extends HBox {
    private String name;
    private double price;
    private int amount;
    private final int type;
    Goods(String name, double price, int amount, int type) {
        // 初始化数据域
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.type = type;

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

        // hang over 样式
        setOnMouseEntered(e-> setStyle(
                "-fx-background-color: #e7e7e7"
        ));

        setOnMouseExited(e-> setStyle(
                "-fx-background-color: #ffffff"
        ));

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

        // 调整间距和位置
        setMinHeight(50);
        setPadding(new Insets(5, 0, 5, 5));
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(20);
    }

    public void updateData() {
        HBox labelBox = (HBox) getChildren().get(0);
        List<Label> labels = new ArrayList<>();
        for (Node node: labelBox.getChildren()) {
            if(node instanceof Label label) {
                labels.add(label);
            }
        }
        labels.get(0).setText(name);
        labels.get(1).setText(String.format("%.2f", price));
        labels.get(2).setText(String.format("%d", amount));
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
