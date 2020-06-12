package com.jungle.twiter.retrofit;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Add_market_server {
    @POST("market/add")
    @FormUrlEncoded
    Observable<String> addMarket(@Field("marketName") String email,
                                 @Field("opningTime") String name,
                                 @Field("closingTime") String username);
}
