package com.AndroidFunitureShopApp.viewmodel;

import com.AndroidFunitureShopApp.model.Categories.Categories;
import com.AndroidFunitureShopApp.model.Categories.CategoriesAPI;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesAPIService {
    private static final String BASE_URL="http://192.168.1.11:8080/Mobile-App-Furniture-Shop/";
    private CategoriesAPI api;


    public CategoriesAPIService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(CategoriesAPI.class);
    }
    public Single<List<Categories>> getCategories(){
        return api.getCategories();
    }
}
