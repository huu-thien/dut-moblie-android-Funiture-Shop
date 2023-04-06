package com.AndroidFunitureShopApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.AndroidFunitureShopApp.databinding.LoginBinding;
import com.AndroidFunitureShopApp.model.Account.Account;
import com.AndroidFunitureShopApp.model.Account.UserInfo;
import com.AndroidFunitureShopApp.view.AccountFragment;
import com.AndroidFunitureShopApp.viewmodel.AccountAPIService;

import java.io.Serializable;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    private LoginBinding binding;
    private String id;
    AccountAPIService accountAPIService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccountFragment.class);

            }
        });


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void login(){
        String username = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        accountAPIService = new AccountAPIService();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(), "Please, enter your username!", Toast.LENGTH_SHORT).show();
        }   else if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please, enter your password!", Toast.LENGTH_SHORT).show();
        }   else {
            compositeDisposable.add(accountAPIService.login(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            accountModel -> {
                                if (accountModel.isSuccess()){
                                    Account account = accountModel.getResult().get(0);
                                    String role = account.getRole().toString();
                                    UserInfo.userInfo = account;

                                    if(role.equals("user")){
                                        Intent intent = new Intent(this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (role.equals("admin")){
                                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    Toast.makeText(getApplicationContext(), accountModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(), accountModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    ));
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
