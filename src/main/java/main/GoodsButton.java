package main;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoodsButton extends Button {
    GoodsButton(int type){
        super();

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
        setOnMouseEntered(e-> setOpacity(0.8));
        setOnMouseExited(e-> setOpacity(1.2));

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
                setOnMouseClicked(e->{
                    Goods goods = (Goods) getParent();
                    Dialog<String[]> dialog = new Dialog<>();
                    dialog.setTitle("修改商品信息");

                    // 设置对话框按钮
                    ButtonType loginButtonType = new ButtonType("确认", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    // 添加多个参数输入字段
                    TextField parameter1 = new TextField(goods.getName());
                    TextField parameter2 = new TextField(String.valueOf(goods.getPrice()));
                    TextField parameter3 = new TextField(String.valueOf(goods.getAmount()));

                    grid.add(new Label("新的名称："), 0, 0);
                    grid.add(parameter1, 1, 0);
                    grid.add(new Label("新的价格："), 0, 1);
                    grid.add(parameter2, 1, 1);
                    grid.add(new Label("新的数量："), 0, 2);
                    grid.add(parameter3, 1, 2);

                    dialog.getDialogPane().setContent(grid);

                    // 处理结果
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == loginButtonType) {
                            goods.setName(parameter1.getText());
                            goods.setPrice(Double.parseDouble(parameter2.getText()));
                            goods.setAmount(Integer.parseInt(parameter3.getText()));
                            goods.updateData();
                        }
                        return null;
                    });

                    // 显示对话框
                    dialog.showAndWait();
                });
                break;
            case 2:
                setText("删除");
                setOnMouseClicked(e->{
                    Goods goods = (Goods) getParent();
                    if(goods.getType() == 1) {
                        // 归还数量到商店
                        VBox root = (VBox) getScene().getRoot();
                        ScrollPane scrollPane = (ScrollPane) (((Shop) root.getChildren().get(0)).getChildren()).get(3);
                        VBox shopBox = (VBox) scrollPane.getContent();

                        // 创建一个列表来存储需要增加的商品信息
                        List<Goods> goodsToAdd = new ArrayList<>();
                        boolean found = false;
                        for (Node node : shopBox.getChildren()) {
                            if (node instanceof Goods shopGoods) {
                                if (Objects.equals(goods.getName(), shopGoods.getName()) && goods.getPrice() == shopGoods.getPrice()) {
                                    shopGoods.setAmount(shopGoods.getAmount() + goods.getAmount());
                                    shopGoods.updateData();
                                    found = true;
                                    break;
                                }
                            }
                        }

                        // 如果没有找到相同的商品，则添加到goodsToAdd列表
                        if (!found) {
                            goodsToAdd.add(new Goods(goods.getName(), goods.getPrice(), goods.getAmount(), 0));
                        }

                        // 循环结束后，添加所有需要添加的商品
                        for (Goods g : goodsToAdd) {
                            shopBox.getChildren().add(g);
                        }

                        // 总价减少
                        ShoppingCar shoppingCar = (ShoppingCar) root.getChildren().get(1);
                        shoppingCar.setTotalPrice(shoppingCar.getTotalPrice() - goods.getAmount()*goods.getPrice());
                        shoppingCar.updateTotalPrice();
                    }
                    // 移除该商品栏
                    HBox parent = (HBox)getParent();
                    VBox parentsParent = (VBox)parent.getParent();
                    parentsParent.getChildren().remove(parent);
                    parentsParent.requestFocus();
                    });
                break;
            case 3:
                setText("修改数量");
                setOnMouseClicked(e->{
                    Goods goods = (Goods) getParent();
                    Dialog<String[]> dialog = new Dialog<>();
                    dialog.setTitle("修改购物车商品数量");

                    // 设置对话框按钮
                    ButtonType loginButtonType = new ButtonType("确认", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    // 添加多个参数输入字段
                    TextField parameter = new TextField(String.valueOf(goods.getAmount()));

                    grid.add(new Label("新的数量："), 0, 0);
                    grid.add(parameter, 1, 0);
                    dialog.getDialogPane().setContent(grid);

                    // 处理结果
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == loginButtonType) {
                            VBox root = (VBox) getScene().getRoot();
                            ShoppingCar shoppingCar = (ShoppingCar) root.getChildren().get(1);
                            ScrollPane scrollPane = (ScrollPane) (((Shop) root.getChildren().get(0)).getChildren()).get(3);
                            VBox shopBox = (VBox) scrollPane.getContent();

                            // 在商店中检索该商品
                            Goods matchedGoods = null;
                            for (Node node : shopBox.getChildren()) {
                                if (node instanceof Goods shopGoods) {
                                    if (Objects.equals(goods.getName(), shopGoods.getName()) && goods.getPrice() == shopGoods.getPrice()) {
                                        matchedGoods = shopGoods;
                                        break;
                                    }
                                }
                            }
                            int newAmount = Integer.parseInt(parameter.getText());
                            if (matchedGoods != null && newAmount - goods.getAmount() < matchedGoods.getAmount()) {
                                matchedGoods.setAmount(matchedGoods.getAmount() - newAmount + goods.getAmount());
                            } else if (matchedGoods != null && newAmount - goods.getAmount() == matchedGoods.getAmount()) {
                                shopBox.getChildren().remove(matchedGoods);
                            } else if (matchedGoods == null && newAmount < goods.getAmount()) {
                                shopBox.getChildren().add(new Goods(goods.getName(), goods.getPrice()
                                        , goods.getAmount() - newAmount, 0));
                            } else if (matchedGoods != null && newAmount - goods.getAmount() > matchedGoods.getAmount()) {
                                newAmount = matchedGoods.getAmount() + goods.getAmount();
                                shopBox.getChildren().remove(matchedGoods);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("提示");
                                alert.setHeaderText(null);
                                alert.getDialogPane().setStyle("-fx-font-size: 14");
                                alert.setContentText("请输入合适的数量!");
                                alert.showAndWait();
                                return null;
                            }

                            shoppingCar.setTotalPrice(shoppingCar.getTotalPrice() + (newAmount - goods.getAmount())*goods.getPrice());
                            goods.setAmount(newAmount);
                            goods.updateData();
                            shoppingCar.updateTotalPrice();
                            if (matchedGoods != null) {
                                matchedGoods.updateData();
                            }
                        }
                        return null;
                    });

                    // 显示对话框
                    dialog.showAndWait();
                });
                break;
        }
    }
}
