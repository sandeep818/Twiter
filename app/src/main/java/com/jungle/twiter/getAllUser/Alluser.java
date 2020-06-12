package com.jungle.twiter.getAllUser;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Alluser {
    @GET("alluser?role=master")
    Observable<String> getAllUser();



}
