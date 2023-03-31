package com.AndroidFunitureShopApp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AndroidFunitureShopApp.R;
import com.AndroidFunitureShopApp.databinding.FragmentCartsBinding;
import com.AndroidFunitureShopApp.databinding.FragmentCategoriesBinding;
import com.AndroidFunitureShopApp.model.Cart.CartItem;
import com.AndroidFunitureShopApp.model.Cart.CartItemAdapter;
import com.AndroidFunitureShopApp.model.EventBus.TinhTongEvent;
import com.AndroidFunitureShopApp.viewmodel.CartsListData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class CartsFragment extends Fragment {
    private FragmentCartsBinding binding;
    private ArrayList<CartItem> cartItems;
    private CartItemAdapter cartItemAdapter;

    private void tinhTongTien() {
        long tongTien = 0;

        for (CartItem item : CartsListData.cartItemList) {
            tongTien += item.getPrice() * item.getQuantity();
        }

        binding.tvTotalCart.setText(String.valueOf(tongTien) + "$");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentCartsBinding.inflate(getLayoutInflater());
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = binding.getRoot();
        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvCartitems.setHasFixedSize(true);
        binding.rvCartitems.setLayoutManager(new LinearLayoutManager(getContext()));
        if (CartsListData.cartItemList.size() > 0) {

            cartItemAdapter = new CartItemAdapter(CartsListData.cartItemList);
            binding.rvCartitems.setAdapter(cartItemAdapter);

            tinhTongTien();
        } else {
            binding.txtNote.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTien(TinhTongEvent event) {
        if (event != null) {
            tinhTongTien();
        }
    }
}