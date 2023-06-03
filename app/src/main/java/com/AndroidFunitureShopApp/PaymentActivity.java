package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.AndroidFunitureShopApp.databinding.ActivityPaymentBinding;
import com.AndroidFunitureShopApp.model.Account.UserInfo;
import com.AndroidFunitureShopApp.model.Cart.CartItem;
import com.AndroidFunitureShopApp.viewmodel.OrderAPIService;
import com.AndroidFunitureShopApp.viewmodel.Utils;
import com.google.gson.Gson;


import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PaymentActivity extends AppCompatActivity {

    private ActivityPaymentBinding binding;
    private OrderAPIService orderAPIService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    long totalPrice;
    int totalItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        orderAPIService = new OrderAPIService();

        totalPrice = getIntent().getLongExtra("totalPrice", 0);
        countItem();

        binding.editAddress.setText(UserInfo.userInfo.getDefaultAdress());
        binding.txtName.setText(UserInfo.userInfo.getFullName());
        binding.txtTotalPrice.setText(totalPrice + "$");
        binding.txtEmail.setText(UserInfo.userInfo.getEmail());
        binding.txtPhone.setText(UserInfo.userInfo.getPhone());
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAddress = binding.editAddress.getText().toString().trim();
                if (TextUtils.isEmpty(strAddress)) {
                    Toast.makeText(getApplicationContext(), "You forget enter address!!!", Toast.LENGTH_SHORT).show();
                } else {
                    String strEmail = UserInfo.userInfo.getEmail();
                    String strPhone = UserInfo.userInfo.getPhone();
                    int idUser = UserInfo.userInfo.getId();

                    Log.d("test", new Gson().toJson(Utils.cartItemList));

                    compositeDisposable.add(orderAPIService.createOrder(strEmail, strPhone, totalPrice, idUser, strAddress, totalItem, new Gson().toJson(Utils.cartItemBuyList))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(accountModel -> {
                                Toast.makeText(getApplicationContext(), "Success add order", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }, throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }));
//                    Utils.cartItemList = new ArrayList<>();
                    for(CartItem item : Utils.cartItemList) {
                        for(CartItem itemBuy : Utils.cartItemBuyList) {
                            if(item.getName() == itemBuy.getName()) {
                                Utils.cartItemList.remove(item);
                                break;
                            }
                        }
                    }
                    Utils.cartItemBuyList = new ArrayList<>();
                }
            }
        });

    }

    private void countItem() {
        totalItem = 0;
        for (int i = 0; i < Utils.cartItemBuyList.size(); i++) {
            totalItem += Utils.cartItemBuyList.get(i).getQuantity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}