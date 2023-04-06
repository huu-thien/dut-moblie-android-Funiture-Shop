package com.AndroidFunitureShopApp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.AndroidFunitureShopApp.databinding.ActivityProductByCategoryBinding;
import com.AndroidFunitureShopApp.databinding.LoginBinding;
import com.AndroidFunitureShopApp.databinding.ShipmentDetailsBinding;
import com.AndroidFunitureShopApp.model.Product.Product;
import com.AndroidFunitureShopApp.model.Product.productsAdapter;
import com.AndroidFunitureShopApp.viewmodel.productsAPIService;

import java.util.ArrayList;

public class EnterDeliveryInforActivity extends AppCompatActivity {
    private ShipmentDetailsBinding binding;
//    private deliveryInforAPIService apiServices;
//    private ArrayList<DeliveryInfor> newInfor;
//    private com.AndroidFunitureShopApp.model.Product.productsAdapter productsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ShipmentDetailsBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

    }
}