package com.AndroidFunitureShopApp.model.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    boolean success;
    String message;
    ArrayList<Order> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Order> getResult() {
        return result;
    }

    public void setResult(ArrayList<Order> result) {
        this.result = result;
    }
}
