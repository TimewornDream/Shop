package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vbox = new VBox();
        vbox.setMinSize(720, 720);
        vbox.setStyle(
                "-fx-background-color: #f5f4f5"
        );
        vbox.getChildren().addAll(new Shop(), new ShoppingCar());
        Scene scene = new Scene(vbox, 720, 720);
        stage.setTitle("购物车管理");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
