package com.jungle.twiter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Intro extends AppCompatActivity {
  ViewPager screenPager;
  Intro_viewPagerAdapter introViewPagerAdapter;
  TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        tabLayout = findViewById(R.id.tabLayout);

        List<ScreenItem> screenItems= new ArrayList<>();
        screenItems.add(new ScreenItem("Trustble","it's ok",R.drawable.aaaaaaaaaaaa));
        screenItems.add(new ScreenItem("Secure","good",R.drawable.bbbbbbbbbbb));
        screenItems.add(new ScreenItem("Easy Payment","Easy Way",R.drawable.easy));

        screenPager= findViewById(R.id.screen_Pager);
        introViewPagerAdapter = new Intro_viewPagerAdapter(this,screenItems);
        screenPager.setAdapter(introViewPagerAdapter);
        tabLayout.setupWithViewPager(screenPager);
    }
}
