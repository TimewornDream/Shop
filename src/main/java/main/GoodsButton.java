package main;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GoodsButton extends Button {
    private final int type;
    GoodsButton(int type){
        super();
        this.type = type;

        // 修改样式
        setStyle(
                "-fx-min-height: 24;" +
                        "-fx-border-radius: 8;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: #4777d1;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: #000000;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-font-size: 16;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);"
        );
        setOnMouseEntered(e->{
            setOpacity(0.8);
        });
        setOnMouseExited(e->{
            setOpacity(1.2);
        });

        // 根据type修改点击事件
        switch (type) {
            case 0:
                setText("购买");
                break;
            case 1:
                setText("修改");
                break;
            case 2:
                setText("删除");
                // 移除该商品栏
                setOnMouseClicked(e->{
                    HBox parent = (HBox)getParent();
                    VBox parentsParent = (VBox)parent.getParent();
                    parentsParent.getChildren().remove(parent);
                    parentsParent.requestFocus();
                });
                break;
            case 3:
                setText("修改数量");
                break;
        }
    }

    public int getType() {
        return type;
    }
}
