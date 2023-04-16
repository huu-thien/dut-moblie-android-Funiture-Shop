package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.AndroidFunitureShopApp.viewmodel.OrderAPIService;
import com.AndroidFunitureShopApp.viewmodel.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewOrderActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    OrderAPIService orderAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        orderAPIService = new OrderAPIService();

        compositeDisposable.add(orderAPIService.viewOrder(Utils.account.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        orderModel -> {
                            Toast.makeText(getApplicationContext(), orderModel.getResult().get(0).getItem().get(0).getNameProduct(), Toast.LENGTH_SHORT).show();
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                )
        );
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