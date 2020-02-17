package com.jungle.twiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView gameIcon;
    TextView wlcmText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameIcon= findViewById(R.id.game);
        wlcmText = findViewById(R.id.appText);
        gameIcon.animate().alpha(1).setDuration(2000);
        wlcmText.animate().alpha(1).setDuration(2000);

       Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,Intro.class);
                startActivity(intent);


               // overridePendingTransition(R.anim.slideright, R.anim.slideleft);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();


            }
        },3000);
    }
}
