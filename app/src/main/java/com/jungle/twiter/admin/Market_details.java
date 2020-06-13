package com.jungle.twiter.admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jungle.twiter.R;

public class Market_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView marketName;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_details);
        ActionBar actionBar =getSupportActionBar();

        marketName = findViewById(R.id.name_market);


        Intent intent= getIntent();
       String market= intent.getStringExtra("market");
       marketName.setText(market);



    }
}