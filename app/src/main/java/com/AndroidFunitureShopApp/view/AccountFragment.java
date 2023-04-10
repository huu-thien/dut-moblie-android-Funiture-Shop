package com.AndroidFunitureShopApp.view;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.AndroidFunitureShopApp.LoginActivity;
import com.AndroidFunitureShopApp.MainActivity;
import com.AndroidFunitureShopApp.R;
import com.AndroidFunitureShopApp.databinding.FragmentAccountBinding;
import com.AndroidFunitureShopApp.model.Account.Account;
import com.AndroidFunitureShopApp.model.Account.AccountModel;
import com.AndroidFunitureShopApp.model.Account.UserInfo;
import com.AndroidFunitureShopApp.viewmodel.AccountAPIService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;
    private Context context;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AccountAPIService accountAPIService;
    int id = UserInfo.userInfo.getId();

    public AccountFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ShowInfo();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateInfo(id);
                ShowInfo();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void ShowInfo(){
        if(UserInfo.userInfo.getUsername() != null) binding.etUsername.setText(UserInfo.userInfo.getUsername());
        if(UserInfo.userInfo.getFullName() != null) binding.etFullName.setText(UserInfo.userInfo.getFullName());
        if(UserInfo.userInfo.getPhone() != null) binding.etPhone.setText(UserInfo.userInfo.getPhone());
        if(UserInfo.userInfo.getEmail() != null) binding.etEmail.setText(UserInfo.userInfo.getEmail());
        if(UserInfo.userInfo.getDefaultAdress() != null) binding.etAddress.setText(UserInfo.userInfo.getDefaultAdress());
    }

    public void UpdateInfo(int Uid){
        accountAPIService = new AccountAPIService();
        String password = binding.etPassword.getText().toString().trim();
        String newpass = binding.etNewPassword.getText().toString().trim();
        String fullname = binding.etFullName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String defaultAdress = binding.etAddress.getText().toString().trim();
        String phone = binding.etPhone.getText().toString().trim();
        String imageAva = "";
        if(TextUtils.isEmpty(fullname)){
            Toast.makeText(context, "Please, enter your Fullname!", Toast.LENGTH_SHORT).show();
        }   else if(TextUtils.isEmpty(password)){
            Toast.makeText(context, "Please, enter your password!", Toast.LENGTH_SHORT).show();
        }   else if(TextUtils.isEmpty(email)){
            Toast.makeText(context, "Please, enter your email!", Toast.LENGTH_SHORT).show();
        }   else if(TextUtils.isEmpty(defaultAdress)){
            Toast.makeText(context, "Please, enter your address!", Toast.LENGTH_SHORT).show();
        }   else if(TextUtils.isEmpty(phone)){
            Toast.makeText(context, "Please, enter your phone!", Toast.LENGTH_SHORT).show();
        }   else {
            if(!password.isEmpty() && newpass.isEmpty() && password.equals(UserInfo.userInfo.getPassword())){
                compositeDisposable.add(accountAPIService.UpdateUser(id, password, fullname, imageAva, defaultAdress, email, phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                accountModel -> {
                                    if (accountModel.isSuccess()){
                                        Account account = accountModel.getResult().get(0);
                                        UserInfo.userInfo = account;
                                        Toast.makeText(context, "Update user information Success!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(context, accountModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }
            else if(!password.isEmpty() && !newpass.isEmpty() && password.equals(UserInfo.userInfo.getPassword())){
                compositeDisposable.add(accountAPIService.UpdateUser(id, newpass, fullname, imageAva, defaultAdress, email, phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                accountModel -> {
                                    if (accountModel.isSuccess()){
                                        Account account = accountModel.getResult().get(0);
                                        UserInfo.userInfo = account;
                                        Toast.makeText(context, "Update user information Success!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(context, accountModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }   else {
                Toast.makeText(context, "Password is not correct!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
        binding = null;
    }

    //Menu Logout Action
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                //finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}