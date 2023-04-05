package com.AndroidFunitureShopApp.viewmodel;

import com.AndroidFunitureShopApp.model.Cart.CartItem;
import com.AndroidFunitureShopApp.model.Product.Product;

import java.util.List;

public class CartsListData {
    //private static final String BASE_URL = "http://192.168.68.201:2024/furnitureshop_v2/"; // ip của Vanh
    private static final String BASE_URL = _Constant.baseUrl;
    public static List<CartItem> cartItemList;

    public static void addToCart(Product product) {
        int id = product.getId();
        String name = product.getName();
        String img = product.getImageUrl();
        int price = product.getPrice();
        int quantity = 1;

        if (CartsListData.cartItemList.size() > 0) {
            boolean check = true;
            for (int i = 0; i < CartsListData.cartItemList.size(); i++) {
                if (CartsListData.cartItemList.get(i).getId() == id) {
                    CartsListData.cartItemList.get(i).setQuantity(CartsListData.cartItemList.get(i).getQuantity() + 1);
                    check = false;
                    break;
                }
            }
            if (check) {
                CartItem cartItem = new CartItem(id, name, quantity, img, price);
                CartsListData.cartItemList.add(cartItem);
            }
        } else {
            CartItem cartItem = new CartItem(id, name, quantity, img, price);
            CartsListData.cartItemList.add(cartItem);
        }
    }

    public static long calTotalPrice() {
        long totalPrice = 0;
        for (CartItem item : CartsListData.cartItemList) {
            totalPrice += item.getQuantity() * item.getPrice();
        }
        return totalPrice;
    }
}
