package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Outline;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.AndroidFunitureShopApp.databinding.ActivityMainBinding;
import com.AndroidFunitureShopApp.viewmodel.CartsListData;
import com.AndroidFunitureShopApp.viewmodel.ViewPagerAdapter;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onResume();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        ChangeTab();

        if (CartsListData.cartItemList == null) {
            CartsListData.cartItemList = new ArrayList<>();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
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
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}