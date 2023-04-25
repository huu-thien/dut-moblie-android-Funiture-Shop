package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.AndroidFunitureShopApp.databinding.ActivitySetNewPassBinding;
import com.AndroidFunitureShopApp.databinding.LoginBinding;
import com.AndroidFunitureShopApp.model.Account.Account;
import com.AndroidFunitureShopApp.model.Account.UserInfo;
import com.AndroidFunitureShopApp.viewmodel.AccountAPIService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SetNewPassActivity extends AppCompatActivity {
    private ActivitySetNewPassBinding binding;
    AccountAPIService accountAPIService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int AccountID = Integer.parseInt(getIntent().getStringExtra("accID"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_pass);
        binding = ActivitySetNewPassBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);


    }

    public void UpdatePass(){
        String pass = binding.etNewPassword.getText().toString().trim();
        String confirmPass = binding.etConfirmPassword.getText().toString().trim();
        accountAPIService = new AccountAPIService();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Please, enter your password!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmPass)) {
            Toast.makeText(getApplicationContext(), "Please, enter your confirm password!", Toast.LENGTH_SHORT).show();
        } else {
            if(!pass.isEmpty() && confirmPass.isEmpty() && pass.equals(confirmPass)){
                compositeDisposable.add(accountAPIService.UpdatePassword(AccountID, pass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                accountModel -> {
                                    if (accountModel.isSuccess()){
                                        Account account = accountModel.getResult().get(0);
                                        UserInfo.userInfo = account;
                                        Toast.makeText(getApplicationContext(), "Update Password Success!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SetNewPassActivity.this, SuccessRepassActivity.class);
                                        startActivity(intent);
                                        finish();
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
    }
}