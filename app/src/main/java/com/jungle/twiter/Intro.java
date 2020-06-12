package com.jungle.twiter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Intro extends AppCompatActivity {
  ViewPager screenPager;
  Intro_viewPagerAdapter introViewPagerAdapter;
  TabLayout tabLayout;
  Button btn_next;
  int position=0;
  Button get_start;
  Animation btn_anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide mobile notification bar & status
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        //check intro already open or not
        if (restorePrefData()){
            Intent intent = new Intent(Intro.this,Login.class);
            startActivity(intent);
            finish();

        }

        btn_next = findViewById(R.id.next);
        get_start = findViewById(R.id.getStarted);
        tabLayout = findViewById(R.id.tabLayout);
        btn_anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);


        final List<ScreenItem> screenItems= new ArrayList<>();
        screenItems.add(new ScreenItem("Play & earn","the best place for you to be entertained and to make earnings with real online money games india.",R.drawable.earn_money));
        screenItems.add(new ScreenItem("Secure","We're excited to introduce Safe App with brand new security features to keep you and your personal data safe and secure.",R.drawable.security));
        screenItems.add(new ScreenItem("Easy Payment","Get Your Wallet Money in Easy and Fast Way.",R.drawable.easy));

        screenPager= findViewById(R.id.screen_Pager);
        introViewPagerAdapter = new Intro_viewPagerAdapter(this,screenItems);
        screenPager.setAdapter(introViewPagerAdapter);
        tabLayout.setupWithViewPager(screenPager);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=screenPager.getCurrentItem();
                if (position < screenItems.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position==screenItems.size()-1){

                    //hide next button and indicators show get started button
                    loadLastScreen();
                }

            }
        });
        //get start to login activity
        get_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro.this,Login.class);
                startActivity(intent);
            // to know this intro already opened or not
            //save boolean in app data
                savePrefsData();
                finish();

            }
        });

        //tableyout change listener
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition()==screenItems.size()-1){
                    //when swipe hide next button and indicators show get started button
                    loadLastScreen();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private boolean restorePrefData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        Boolean isIntroOpenedBefore= preferences.getBoolean("isIntroOpened",false);
        return isIntroOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.commit();
    }

    private void loadLastScreen() {
        //hide next button and indicators show get started button
        btn_next.setVisibility(View.INVISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        get_start.setVisibility(View.VISIBLE);

        //add animation
        get_start.setAnimation(btn_anim);

    }
}
