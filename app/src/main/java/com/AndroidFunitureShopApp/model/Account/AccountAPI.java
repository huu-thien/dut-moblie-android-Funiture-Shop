package com.AndroidFunitureShopApp.model.Account;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccountAPI {
    @POST("register.php")
    @FormUrlEncoded
    Observable<AccountModel> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("role") String role,
            @Field("fullname") String fullname,
            @Field("imageAva") String imageAva,
            @Field("defaultAdress") String defaultAdress,
            @Field("email") String email,
            @Field("phone") String phone
    );

    @POST("login.php")
    @FormUrlEncoded
    Observable<AccountModel> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("updateUser.php")
    @FormUrlEncoded
    Observable<AccountModel> updateUser(
            @Field("id") int id,
            @Field("password") String password,
            //@Field("role") String role,
            @Field("fullname") String fullname,
            @Field("imageAva") String imageAva,
            @Field("defaultAdress") String defaultAdress,
            @Field("email") String email,
            @Field("phone") String phone
    );
}
