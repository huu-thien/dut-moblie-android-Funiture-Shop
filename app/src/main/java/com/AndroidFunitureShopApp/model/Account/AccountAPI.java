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
            @Field("phone") String phone,
            @Field("uid") String uid
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

    @POST("checkpass2.php")
    @FormUrlEncoded
    Observable<AccountModel> checkpass2(
            @Field("username") String username,
            @Field("pass2") String pass2
    );

    @POST("updatePassword.php")
    @FormUrlEncoded
    Observable<AccountModel> updatePassword(
            @Field("id") int id,
            @Field("password") String password
    );

    @POST("updateToken.php")
    @FormUrlEncoded
    Observable<AccountModel> updateToken(
            @Field("id") int id,
            @Field("token") String token
    );
    @POST("updateStatus.php")
    @FormUrlEncoded
    Observable<AccountModel> updateStatus(
            @Field("id") int id,
            @Field("status") int status
    );

    @POST("getToken.php")
    @FormUrlEncoded
    Observable<AccountModel> getToken(
            @Field("status") int status
    );
}
