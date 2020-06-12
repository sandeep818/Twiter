package com.jungle.twiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jungle.twiter.admin.Home_admin;
import com.jungle.twiter.master.Master_home;
import com.jungle.twiter.retrofit.Inodejs;
import com.jungle.twiter.retrofit.RetrofitClient;

import org.json.JSONObject;

import in.codeshuffle.typewriterview.TypeWriterView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    Inodejs myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    EditText username;
    EditText password;
    Button login;
    String user_name;
    String user_pass;
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (restorePrefData()){
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
            String userName= preferences.getString("username","user");
            String user_role= preferences.getString("role","NaN");
            Toast.makeText(this, " "+restorePrefData(), Toast.LENGTH_SHORT).show();
           if (user_role.equals("admin")){
               Intent intent = new Intent(Login.this, Home_admin.class);
               intent.putExtra("user",userName);
               startActivity(intent);
//                                //*****************animation when witch activity*********************
               overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
               finish();
           }else if (user_role.equals("Master")){
               Intent intent = new Intent(Login.this, Master_home.class);
               intent.putExtra("user",userName);
               startActivity(intent);
//                                //*****************animation when witch activity*********************
               overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
               finish();

           }
        }

        //int api

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(Inodejs.class);

        login=findViewById(R.id.login);
       username=findViewById(R.id.username_input);
       password =findViewById(R.id.password_input);
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    login.performClick();
                }


                return false;
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name= username.getText().toString();
                user_pass= password.getText().toString();

                loginUser(user_name,user_pass);


         //       if (user_name.equals("") || user_pass.equals("")){
          //          Toast.makeText(Login.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
 //               }else{
//                    ParseUser.logInInBackground(user_name,user_pass, new LogInCallback() {
//                        @Override
//                        public void done(ParseUser user, ParseException e) {
//                            if (user!=null){
//                                Intent intent = new Intent(Login.this,Home.class);
////                                intent.putExtra("user",user_name);
//
//                                startActivity(intent);
//                                //*****************animation when witch activity*********************
//                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                                finish();
//                            }else{
//                                Toast.makeText(Login.this, "Wrong Password "+e, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
            //    }
            }
        });


      //  ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("User_Tbl");



        //Create Object and refer to layout view
        TypeWriterView typeWriterView=(TypeWriterView)findViewById(R.id.typeWriterView);

        //Setting each character animation delay
        typeWriterView.setDelay(1);

        //Setting music effect On/Off
        typeWriterView.setWithMusic(false);

        //Animating Text
        typeWriterView.animateText("Please Login...!");
    }

    private void loginUser(String username, String password) {
        compositeDisposable.add(myAPI.loginUser(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        if (s.contains("password")) {
                            JSONObject object = new JSONObject(s);
                            if (!restorePrefData()){
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
                                savePrefsData(object.getString("username"),password,object.getString("role"),object.getString("fullname"));
                            }


                            Toast.makeText(Login.this, object.getString("fullname") +"  ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this,Home.class);
                            intent.putExtra("user",object.getString("fullname"));
                            startActivity(intent);
//                                //*****************animation when witch activity*********************
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                finish();
                        }else {
                            Toast.makeText(Login.this, s +"  ", Toast.LENGTH_SHORT).show();
                        }
                    }



                })
        );

    }

    private boolean restorePrefData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        Boolean isIntroOpenedBefore= preferences.getBoolean("isLogin",false);
        return isIntroOpenedBefore;
    }

    private void savePrefsData(String username,String password,String role, String fullname) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("loginDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();
        editor.putBoolean("isLogin",true);
        editor.putString("username",username);
        editor.putString("fullname",fullname);
        editor.putString("password",password);
        editor.putString("role",role);
        editor.commit();
    }
}
