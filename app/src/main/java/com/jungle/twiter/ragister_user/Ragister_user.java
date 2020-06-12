package com.jungle.twiter.ragister_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jungle.twiter.R;
import com.jungle.twiter.retrofit.Inodejs;
import com.jungle.twiter.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Ragister_user extends AppCompatActivity {

    Inodejs myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Button ragister;
    EditText name,username,password,email,mobile,role,credit_ref,exposer_limit,upline,downline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister_user);
        ragister = findViewById(R.id.ragister_user);
        name = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        role = findViewById(R.id.role);
        credit_ref = findViewById(R.id.credit_ref);
        exposer_limit = findViewById(R.id.exposer_limit);
//        upline = findViewById(R.id.upline);
//        downline = findViewById(R.id.downline);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        String userName= preferences.getString("username","user");

        //int api

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(Inodejs.class);


        ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compositeDisposable.add(myAPI.ragisterUser(email.getText().toString(),name.getText().toString(),username.getText().toString(),
                        password.getText().toString(),role.getText().toString(),mobile.getText().toString(),credit_ref.getText().toString(),
                        exposer_limit.getText().toString(),userName,downline.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                Toast.makeText(Ragister_user.this, name.getText().toString()+" As User "+s, Toast.LENGTH_SHORT).show();
                            }
                        }));
            }
        });
    }
}
