package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.AndroidFunitureShopApp.databinding.ActivityRegisterBinding;
import com.AndroidFunitureShopApp.model.Account.AccountAPI;
import com.AndroidFunitureShopApp.model.Account.AccountModel;
import com.AndroidFunitureShopApp.view.AccountFragment;
import com.AndroidFunitureShopApp.viewmodel.AccountAPIService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    AccountAPIService accountAPIService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean valid = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void register() {
        accountAPIService = new AccountAPIService();
        String str_username = binding.etUsername.getText().toString().trim();
        String str_fullname = binding.etName.getText().toString().trim();
        String str_password = binding.etPassword.getText().toString().trim();
        String str_repass = binding.etRepass.getText().toString().trim();
        String str_role = "user";
        String str_imageAva = "";
        String str_defaultAdress = "";
        String str_email = "";
        String str_phone = "";

        if(TextUtils.isEmpty(str_username)){
            Toast.makeText(getApplicationContext(), "Please, enter your username!", Toast.LENGTH_SHORT).show();
        }   else if(TextUtils.isEmpty(str_fullname)){
            Toast.makeText(getApplicationContext(), "Please, enter your name!", Toast.LENGTH_SHORT).show();
        }   else if(TextUtils.isEmpty(str_password)){
            Toast.makeText(getApplicationContext(), "Please, enter your password!", Toast.LENGTH_SHORT).show();
        }   else if(TextUtils.isEmpty(str_repass)){
            Toast.makeText(getApplicationContext(), "Please, Reenter your password!", Toast.LENGTH_SHORT).show();
        }   else {
            if(str_password.equals(str_repass)){
                compositeDisposable.add(accountAPIService.register(str_username, str_password, str_role, str_fullname, str_imageAva, str_defaultAdress, str_email, str_phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                accountModel -> {
                                    if (accountModel.isSuccess()){
                                        Toast.makeText(getApplicationContext(), "Register Success!", Toast.LENGTH_SHORT).show();
                                        valid = true;
                                    }else {
                                        Toast.makeText(getApplicationContext(), accountModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }
            else {
                Toast.makeText(getApplicationContext(), "Password & Confirm Password do not match!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}