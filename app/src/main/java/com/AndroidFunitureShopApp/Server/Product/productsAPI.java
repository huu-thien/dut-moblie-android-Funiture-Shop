package com.AndroidFunitureShopApp.model.Product;

import com.AndroidFunitureShopApp.model.Product.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface productsAPI {
    @GET("getProducts.php")
    Single<List<Product>> getProducts();

    @GET("getProductsByCategories.php")
    Single<List<Product>> getProductsByCategories(
            @Query("categoryId") int categoryId
    );
    @POST("searchProduct.php")
    @FormUrlEncoded
    Single<List<Product>> searchProduct(
            @Field("search") String search
    );
}
