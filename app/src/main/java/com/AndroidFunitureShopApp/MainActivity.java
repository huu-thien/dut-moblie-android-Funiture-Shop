package com.AndroidFunitureShopApp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.AndroidFunitureShopApp.databinding.ActivityMainBinding;
import com.AndroidFunitureShopApp.viewmodel.ViewPagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
        mapping();
    }

    private void mapping() {

    }


    //Click vào mỗi item dưới navigation bottom thì hiện fragment tương ứng
    private void ChangeTab() {
        bottomNavigationView = binding.bottomNav;
        viewPager = binding.viewPager;
        binding.bottomNav.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    Log.d("DEBUG","0");
//                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(0);
                    break;
                case  R.id.action_category:
                    Log.d("DEBUG","1");
//                    Toast.makeText(MainActivity.this, "Category", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.action_account:
                    Log.d("DEBUG","2");
//                    Toast.makeText(MainActivity.this, "Your Account", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.action_cart:
                    Log.d("DEBUG","3");
//                    Toast.makeText(MainActivity.this, "Your Cart", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(3);
                    break;
            };

            return true;
        });
        setUpViewPager();
    }

    // Vuốt từ fragment này sang fragment khác
    private void setUpViewPager() {
        Log.d("DEBUG","setUpViewPager");
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

    //Menu Logout Action
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void UpdateUser(String uid){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Update Success", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Update Fail", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}