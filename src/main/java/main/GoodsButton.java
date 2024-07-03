package main;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

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
                setOnMouseClicked(e->{
                    Goods goods = (Goods) getParent();
                    // 获取 shoppingCarBox
                    VBox root = (VBox) getScene().getRoot();
                    ScrollPane scrollPane = (ScrollPane) (((ShoppingCar) root.getChildren().get(1)).getChildren()).get(2);
                    VBox shoppingCarBox = (VBox) scrollPane.getContent();
                    // 检查该商品是否已存在
                    boolean flag = true;
                    for (Node node: shoppingCarBox.getChildren()) {
                        if (!(node instanceof Goods shoppingCarGoods)) {
                            continue;
                        }
                        if (Objects.equals(shoppingCarGoods.getName(), goods.getName()) && shoppingCarGoods.getPrice() == goods.getPrice()) {
                            shoppingCarGoods.setAmount(shoppingCarGoods.getAmount() + 1);
                            shoppingCarGoods.updateData();
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        shoppingCarBox.getChildren().add(new Goods(goods.getName(), goods.getPrice(), 1, 1));
                    }

                    // 商店数量--
                    goods.setAmount(goods.getAmount() - 1);
                    goods.updateData();
                    // 若数量为0，则删除
                    if(goods.getAmount() == 0) {
                        HBox parent = (HBox)getParent();
                        VBox parentsParent = (VBox)parent.getParent();
                        parentsParent.getChildren().remove(parent);
                        parentsParent.requestFocus();
                    }
                    ShoppingCar shoppingCar = ((ShoppingCar) root.getChildren().get(1));
                    shoppingCar.setTotalPrice(shoppingCar.getTotalPrice() + goods.getPrice());
                    shoppingCar.updateTotalPrice();
                });
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
