package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.AndroidFunitureShopApp.databinding.ActivityForgotPassBinding;
import com.AndroidFunitureShopApp.databinding.LoginBinding;
import com.AndroidFunitureShopApp.model.Account.Account;
import com.AndroidFunitureShopApp.model.Account.UserInfo;
import com.AndroidFunitureShopApp.viewmodel.AccountAPIService;
import com.AndroidFunitureShopApp.viewmodel.Utils;

import java.util.Set;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ForgotPassActivity extends AppCompatActivity {

    private ActivityForgotPassBinding binding;
    AccountAPIService accountAPIService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPassBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.forgetPasswordBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnForgotNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checPass2();
            }
        });

    }

    public void checPass2(){
        String username = binding.etForgotUsername.getText().toString().trim();
        String password2 = binding.etForgotPass2.getText().toString().trim();
        accountAPIService = new AccountAPIService();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getApplicationContext(), "Please, enter your username!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password2)) {
            Toast.makeText(getApplicationContext(), "Please, enter your password level 2!", Toast.LENGTH_SHORT).show();
        } else {
            compositeDisposable.add(accountAPIService.checkPass2(username, password2)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            accountModel -> {
                                if (accountModel.isSuccess()) {
                                    Account account = accountModel.getResult().get(0);
                                    String accID = String.valueOf(account.getId());
                                    Log.d("DEBUG", "Account success");
                                    //UserInfo.userInfo = account;
                                    Intent intent = new Intent(this, SetNewPassActivity.class);
                                    intent.putExtra("accID", accID);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), accountModel.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("DEBUG", "Account unsucess");
                                    Toast.makeText(getApplicationContext(), accountModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    ));
        }
    }


}