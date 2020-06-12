package com.jungle.twiter.retrofit;


import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null){
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
//                    .callTimeout(2, TimeUnit.MINUTES)
//                    .connectTimeout(20, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .writeTimeout(30, TimeUnit.SECONDS);

            instance = new Retrofit.Builder()
                    .baseUrl("http://192.168.43.173:3000")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                    .client(httpClient.build())
                    .build();



        }
        return instance;
    }
}
