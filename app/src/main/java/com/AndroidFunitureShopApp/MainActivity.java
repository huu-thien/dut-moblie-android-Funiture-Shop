package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.AndroidFunitureShopApp.databinding.ActivityMainBinding;
import com.AndroidFunitureShopApp.model.Account.AccountAPI;
import com.AndroidFunitureShopApp.model.Account.UserInfo;
import com.AndroidFunitureShopApp.view.AccountFragment;
import com.AndroidFunitureShopApp.viewmodel.AccountAPIService;
import com.AndroidFunitureShopApp.viewmodel.Utils;
import com.AndroidFunitureShopApp.viewmodel.ViewPagerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    String user_id;
    private BottomNavigationView bottomNavigationView;
    AccountAPIService accountAPIService = new AccountAPIService();
    private ViewPager viewPager;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onResume();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        ChangeTab();
        if (Utils.cartItemList == null) {
            Utils.cartItemList = new ArrayList<>();
        }
        SetUserInfo();
        getToken();
//        binding.searchBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchActivityProcess();
//            }
//        });
//

    }

    private void getToken() {

        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            compositeDisposable.add(accountAPIService.UpdateToken(Utils.account.getId(), s)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            messageModel -> {

                                            },
                                            throwable -> {
                                                Log.d("DEBUG", throwable.getMessage());
                                            }
                                    ));
                        }
                    }
                });
        compositeDisposable.add(accountAPIService.GetToken(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {

                            if (userModel.isSuccess()) {
                                Utils.ID_RECEIVED = String.valueOf(userModel.getResult().get(0).getId());
                                Log.d("DEBUG", Utils.ID_RECEIVED);
                            }
                        },
                        throwable -> {
                            Log.d("DEBUG", throwable.getMessage());
                        }
                ));


    }

    private void searchActivityProcess() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    private void SetUserInfo() {
        AccountFragment fragment = new AccountFragment();
        Intent intent = getIntent();
        user_id = intent.getStringExtra("account_ID");
        Bundle bundle = new Bundle();

        bundle.putString("account_ID", user_id);
        fragment.setArguments(bundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//        if (!isLoggedIn) {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

    //Click vào mỗi item dưới navigation bottom thì hiện fragment tương ứng
    private void ChangeTab() {
        bottomNavigationView = binding.bottomNav;
        viewPager = binding.viewPager;
        binding.bottomNav.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    Log.d("DEBUG", "0");
//                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.action_category:
                    Log.d("DEBUG", "1");
//                    Toast.makeText(MainActivity.this, "Category", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.action_account:
                    Log.d("DEBUG", "2");
//                    Toast.makeText(MainActivity.this, "Your Account", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.action_cart:
                    Log.d("DEBUG", "3");
//                    Toast.makeText(MainActivity.this, "Fragment 4", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(3);
                    break;
                case R.id.action_chat:
                    Log.d("DEBUG", "4");
//                    Toast.makeText(MainActivity.this, "Fragment 5", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(4);
                    break;
            }
            ;

            return true;
        });
        setUpViewPager();
    }

    // Vuốt từ fragment này sang fragment khác
    private void setUpViewPager() {
        Log.d("DEBUG", "setUpViewPager");
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_category).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_account).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.action_cart).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.action_chat).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}