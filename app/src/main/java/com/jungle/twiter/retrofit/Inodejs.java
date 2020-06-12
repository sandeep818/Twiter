package com.jungle.twiter.retrofit;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Inodejs {
    @POST("ragister/master")
    @FormUrlEncoded
    Observable<String> ragisterMser(@Field("email") String email,
                                    @Field("name") String name,
                                    @Field("username") String username,
                                    @Field("password") String password,
                                    @Field("role") String role,
                                    @Field("mobile") String mobile,
                                    @Field("creditReference") String creditReference,
                                    @Field("exposerLimit") String exposerLimit,
                                    @Field("upline") String upline,
                                    @Field("createdBy") String createdBy,
                                    @Field("downline") String downline);
    @POST("ragister/user")
    @FormUrlEncoded
    Observable<String> ragisterUser(@Field("email") String email,
                                    @Field("name") String name,
                                    @Field("username") String username,
                                    @Field("password") String password,
                                    @Field("role") String role,
                                    @Field("mobile") String mobile,
                                    @Field("creditReference") String creditReference,
                                    @Field("exposerLimit") String exposerLimit,
                                    @Field("createdBy") String createdBy,
                                    @Field("downline") String downline);
    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("username") String username, @Field("password") String password);
}
