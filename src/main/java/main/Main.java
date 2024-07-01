package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // 根节点
        VBox vbox = new VBox();
        vbox.setMinSize(720, 720);
        vbox.setStyle(
                "-fx-background-color: #f5f4f5"
        );
        vbox.getChildren().addAll(new Shop(), new ShoppingCar());

        // 设置图标
        Image icon = null;
        try {
            icon = new Image(Objects.requireNonNull(getClass().getResource("/img/shopping_car.png")).toExternalForm());
        } catch (Exception e) {
            System.err.println("Failed to load image: /img/shopping_car.png");
            e.printStackTrace();
        }

        Scene scene = new Scene(vbox, 720, 720);
        stage.setTitle("购物车管理");

        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
