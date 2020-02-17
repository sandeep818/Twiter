package com.jungle.twiter;


import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("k2dj9cDoW5Qz1x5qyx79qaBIyS9b3610QaxUj3Pu")
                .clientKey("CsktlCcIjvaCWwrrrQzvQvRZIPYyg3IjQbHUwlz2")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
