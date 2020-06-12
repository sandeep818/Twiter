package com.jungle.twiter.admin;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jungle.twiter.R;
import com.jungle.twiter.retrofit.Add_market_server;
import com.jungle.twiter.retrofit.Inodejs;
import com.jungle.twiter.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Add_market extends AppCompatActivity {
 public     EditText marketName,opningTime,closingTime;
   public Button submitMarkett;
    Inodejs myAPI;
    Add_market_server add_marketServerApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_market);
        marketName = (EditText) findViewById(R.id.market_name);
        opningTime = (EditText) findViewById(R.id.opning_time);
        closingTime = (EditText) findViewById(R.id.closing_time);
        submitMarkett = findViewById(R.id.market_submitt);

        //int api

        Retrofit retrofit = RetrofitClient.getInstance();
        add_marketServerApi = retrofit.create(Add_market_server.class);


        submitMarkett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Add_market.this, "working", Toast.LENGTH_SHORT).show();
                compositeDisposable.add(add_marketServerApi.addMarket(marketName.getText().toString(),opningTime.getText().toString(),closingTime.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                Toast.makeText(Add_market.this, marketName.getText().toString()+" Added in market"+s, Toast.LENGTH_SHORT).show();
                            }
                        }));

            }
        });
    }
}
