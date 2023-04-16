package com.AndroidFunitureShopApp.model.Item;

public class Item {
    int idProduct;
    String nameProduct;

    public Item(int idProduct, String nameProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
