package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.AndroidFunitureShopApp.databinding.ActivityMainBinding;
import com.AndroidFunitureShopApp.databinding.ActivityProductDetailBinding;
import com.AndroidFunitureShopApp.model.Cart.CartItem;
import com.AndroidFunitureShopApp.model.Product.Product;
import com.AndroidFunitureShopApp.viewmodel.CartsListData;
import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {
    private ActivityProductDetailBinding binding;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
        }

        binding.tvProductDetailName.setText(product.getName().toString());
        binding.tvProductDetailPrice.setText("$" + product.getPrice());
        Picasso.get().load(product.getImageUrl().toString()).into(binding.imgProductDetail);

        binding.btnAddToCarProdudctDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartsListData.addToCart(product);
            }
        });
    }


}