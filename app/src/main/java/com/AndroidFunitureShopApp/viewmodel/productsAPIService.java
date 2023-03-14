package com.AndroidFunitureShopApp.viewmodel;//package com.AndroidFunitureShopApp.viewmodel;
import com.AndroidFunitureShopApp.model.Product.Product;
import com.AndroidFunitureShopApp.model.Product.productsAPI;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class productsAPIService {


    private static final String BASE_URL="http://192.168.1.2:8080/Mobile-App-Furniture-Shop/"; // ip của Ni
//    private static final String BASE_URL="http://192.168.1.11:8080/Mobile-App-Furniture-Shop/"; // ip của Thiện đẹp trai
    private productsAPI api;


    public productsAPIService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(productsAPI.class);
    }
    public Single<List<Product>> getProducts(){
        return api.getProducts();
    }

}