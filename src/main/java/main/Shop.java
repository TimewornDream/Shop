package main;

import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
                        "-fx-min-width: 20;" +
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
                        "-fx-min-width: 20;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: #000000;" +
                        "-fx-font-size: 16"
        );

        // 数量输入文本框
        TextField amountInput = new TextField();
        amountInput.setPromptText("数量");
        amountInput.setStyle(
                "-fx-min-height: 50;" +
                        "-fx-min-width: 76;" +
                        "-fx-max-width: 76;" +
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

        addButton.setOnMouseEntered(e->{
            addButton.setOpacity(0.8);
        });
        addButton.setOnMouseExited(e->{
            addButton.setOpacity(1.2);
        });

        // 调整inputBox子组件间距和位置
        inputBox.getChildren().addAll(nameInput, priceInput, amountInput, addButton);
        inputBox.setSpacing(12);
        inputBox.setAlignment(Pos.TOP_CENTER);

        // 页面中部，商品展示部分
        VBox shopBox = new VBox();
        shopBox.setMinSize(640, 310);
        shopBox.setStyle(
                "-fx-background-color: #ffffff;"
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
        shopBox.getChildren().addAll(labelBox, new Goods("苹果", 12, 2, 0));
        for (int i = 0; i < 10; i++) {
            shopBox.getChildren().add(new Goods("苹果", 12, 2, 0));
        }

        // 添加滚动条
        ScrollPane scrollPane = new ScrollPane(shopBox);
        scrollPane.setMinSize(640, 320);
        scrollPane.setMaxSize(640, 320);
        scrollPane.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-border-color: #000000;" +
                        "-fx-border-width: 2;" +
                        "-fx-padding: 3"
        );
        scrollPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
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


        // 添加商品按钮点击事件
        addButton.setOnMouseClicked(e->{
            // 检查输入是否异常
            if (isNumeric(nameInput.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                alert.getDialogPane().setStyle("-fx-font-size: 14");
                alert.setContentText("请输入正确的名称!");
                alert.showAndWait();
            } else if (!(isNumeric(priceInput.getText()) && Double.parseDouble(priceInput.getText()) >= 0)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                alert.getDialogPane().setStyle("-fx-font-size: 14");
                alert.setContentText("请输入正确的价格!");
                alert.showAndWait();
            } else if (!(isNumeric(amountInput.getText()) && Double.parseDouble(amountInput.getText()) > 0)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                alert.getDialogPane().setStyle("-fx-font-size: 14");
                alert.setContentText("请输入正确的数量!");
                alert.showAndWait();
            }
            shopBox.getChildren().add(new Goods(nameInput.getText(), Double.parseDouble(priceInput.getText())
                    , Integer.parseInt(amountInput.getText()), 0));

        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(label1, inputBox, label2, scrollPane);
    }
    public static boolean isNumeric(String str) {
        return str != null && str.chars().allMatch(Character::isDigit);
    }
}
