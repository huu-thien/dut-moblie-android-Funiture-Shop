package com.AndroidFunitureShopApp.viewmodel;

import com.AndroidFunitureShopApp.model.Account.Account;
import com.AndroidFunitureShopApp.model.Cart.CartItem;
import com.AndroidFunitureShopApp.model.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String BASE_URL = _Constant.baseUrl;
    public static List<CartItem> cartItemList;
    public static List<CartItem> cartItemBuyList = new ArrayList<>();
    public static Account account;

    public static void addToCart(Product product) {
        int id = product.getId();
        String name = product.getName();
        String img = product.getImageUrl();
        int price = product.getPrice();
        int quantity = 1;

        if (Utils.cartItemList.size() > 0) {
            boolean check = true;
            for (int i = 0; i < Utils.cartItemList.size(); i++) {
                if (Utils.cartItemList.get(i).getId() == id) {
                    Utils.cartItemList.get(i).setQuantity(Utils.cartItemList.get(i).getQuantity() + 1);
                    check = false;
                    break;
                }
            }
            if (check) {
                CartItem cartItem = new CartItem(id, name, quantity, img, price);
                Utils.cartItemList.add(cartItem);
            }
        } else {
            CartItem cartItem = new CartItem(id, name, quantity, img, price);
            Utils.cartItemList.add(cartItem);
        }
    }

    public static void addToBuyCart(CartItem product) {
        int id = product.getId();
        String name = product.getName();
        String img = product.getImageUrl();
        int price = product.getPrice();
        int quantity = product.getQuantity();

        if (Utils.cartItemBuyList.size() > 0) {
            boolean check = true;
            for (int i = 0; i < Utils.cartItemBuyList.size(); i++) {
                if (Utils.cartItemBuyList.get(i).getId() == id) {
                    Utils.cartItemBuyList.get(i).setQuantity(Utils.cartItemBuyList.get(i).getQuantity() + 1);
                    check = false;
                    break;
                }
            }
            if (check) {
                CartItem cartItem = new CartItem(id, name, quantity, img, price);
                Utils.cartItemBuyList.add(cartItem);
            }
        } else {
            CartItem cartItem = new CartItem(id, name, quantity, img, price);
            Utils.cartItemBuyList.add(cartItem);
        }
    }

}
