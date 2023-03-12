package com.AndroidFunitureShopApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.AndroidFunitureShopApp.databinding.ActivityMainBinding;
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

    private void ActionSlider() {
        List<String> sliders = new ArrayList<>();
        sliders.add("https://hstatic.net/336/1000150336/1000203404/slideshow_3.jpg?v=24");
        sliders.add("https://theme.hstatic.net/200000584705/1000969925/14/home_slider_item_image_3.png?v=2524");
        sliders.add("https://hstatic.net/336/1000150336/1000203404/slideshow_2.jpg?v=24");
//        sliders.add("https://theme.hstatic.net/200000584705/1000969925/14/home_slider_item_image_1.png?v=2526");
        sliders.add("https://hstatic.net/336/1000150336/1000203404/slideshow_1.jpg?v=24");
        sliders.add("https://theme.hstatic.net/200000584705/1000969925/14/home_slider_item_image_2.png?v=2524");
        for (int i = 0; i < sliders.size(); i++) {
            ImageView imgView = new ImageView(getApplicationContext());
            Picasso.get().load(sliders.get(i)).into(imgView);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgView.setClipToOutline(true);
            imgView.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    int size = getResources().getDimensionPixelSize(R.dimen.image_rounding_size);
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), size);
                }
            });
            binding.vfSlider.addView(imgView);
        }
        binding.vfSlider.setFlipInterval(3000);
        binding.vfSlider.setAutoStart(true);

        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slider_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slider_out_right);

        binding.vfSlider.setInAnimation(slide_in);
        binding.vfSlider.setOutAnimation(slide_out);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        ActionSlider();
        //ChangeTab();
        //setUpViewPager();



    }

    //Click vào mỗi item dưới navigation bottom thì hiện fragment tương ứng
    private void ChangeTab() {
        bottomNavigationView = binding.bottomNav;
        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(0);
                    break;
                case  R.id.action_category:
                    Toast.makeText(MainActivity.this, "Category", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.action_account:
                    Toast.makeText(MainActivity.this, "Your Account", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.action_cart:
                    Toast.makeText(MainActivity.this, "Your Cart", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(3);
                    break;
            };

            return true;
        });
    }

    // Vuốt từ fragment này sang fragment khác
    private void setUpViewPager() {
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