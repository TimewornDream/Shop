package main;

import com.google.gson.Gson;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCar extends VBox {
    private double totalPrice = 0;
    ShoppingCar(){
        super();

        // 购物车顶部文字
        Label labelTop = new Label(String.format("购物车   总价：%.2f元", totalPrice));
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
        scrollPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        // 清空购物车按钮
        Button clearButton = new Button("清空购物车");
        clearButton.setStyle(
                "-fx-min-height: 50;" +
                        "-fx-min-width: 50;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #4777d1;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: #000000;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-size: 16;" +
                        "-fx-cursor: hand;"
        );
        clearButton.setOnMouseEntered(e-> clearButton.setOpacity(0.8));
        clearButton.setOnMouseExited(e-> clearButton.setOpacity(1.2));
        clearButton.setOnMouseClicked(e->{
            List<Goods> goodsList = new ArrayList<>();
            for (Node node: shoppingCarBox.getChildren()) {
                if (node instanceof Goods goods) {
                    goodsList.add(goods);
                }
            }
            for (Goods goods: goodsList) {
                GoodsButton goodsButton = (GoodsButton) goods.getChildren().get(2);
                goodsButton.removeShoppingCarGoods();
            }
        });
        clearButton.setTranslateY(20);

        getChildren().addAll(new Label(), labelTop, scrollPane, clearButton);
        setAlignment(Pos.TOP_CENTER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // 加载数据
        loadData();
        initTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void updateTotalPrice() {
        if(totalPrice < 0) {
            totalPrice = 0 - totalPrice;
        }
        Label labelTop = (Label) getChildren().get(1);
        labelTop.setText(String.format("购物车   总价：%.2f元", totalPrice));
    }

    public void saveData() {
        Gson gson = new Gson();
        List<GoodsData> goodsList = new ArrayList<>();

        ScrollPane scrollPane = (ScrollPane) getChildren().get(2);
        VBox shoppingCarBox = (VBox) scrollPane.getContent();
        for (Node node: shoppingCarBox.getChildren()) {
            if (node instanceof Goods goods) {
                goodsList.add(new GoodsData(goods.getName(), goods.getPrice(), goods.getAmount()));
            }
        }

        Path path = Paths.get("data", "shopping_car_data.json");

        GoodsDataWrapper wrapper = new GoodsDataWrapper();
        wrapper.data = goodsList;


        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(String.valueOf(path)),
                        StandardCharsets.UTF_8))) {

            // 序列化List<GoodsData>为JSON字符串
            String json = gson.toJson(wrapper);

            // 写入JSON字符串到文件
            writer.write(json);

            System.out.println("SHOPPING_CAR: Successfully wrote JSON object to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData(){
        Path path = Paths.get("data", "shopping_car_data.json");
        Gson gson = new Gson();
        ScrollPane scrollPane = (ScrollPane) getChildren().get(2);
        VBox shoppingCarBox = (VBox) scrollPane.getContent();

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path), StandardCharsets.UTF_8))) {
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            GoodsDataWrapper wrapper = gson.fromJson(jsonString.toString(), GoodsDataWrapper.class);
            for (GoodsData goodsData: wrapper.data) {
                shoppingCarBox.getChildren().add(new Goods(goodsData.getName(), goodsData.getPrice(), goodsData.getAmount(), 1));
            }

            System.out.println("SHOPPING_CAR: Successfully load JSON object from file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTotalPrice() {
        ScrollPane scrollPane = (ScrollPane) getChildren().get(2);
        VBox shoppingCarBox = (VBox) scrollPane.getContent();
        totalPrice = 0;
        for (Node node: shoppingCarBox.getChildren()) {
            if (node instanceof Goods goods) {
                totalPrice += goods.getPrice() * goods.getAmount();
            }
        }
        updateTotalPrice();
    }
}