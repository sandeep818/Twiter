package com.jungle.twiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import in.codeshuffle.typewriterview.TypeWriterView;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    String user_name;
    String user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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


                if (user_name.equals("") || user_pass.equals("")){
                    Toast.makeText(Login.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }else{
                    ParseUser.logInInBackground(user_name,user_pass, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user!=null){
                                Intent intent = new Intent(Login.this,Home.class);
//                                intent.putExtra("user",user_name);

                                startActivity(intent);
                                //*****************animation when witch activity*********************
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                finish();
                            }else{
                                Toast.makeText(Login.this, "Wrong Password "+e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
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
}
