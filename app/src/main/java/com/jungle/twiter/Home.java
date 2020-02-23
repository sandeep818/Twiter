package com.jungle.twiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    TextView greetings;
    TextView username;
    ImageView bgimage;
    BottomNavigationView navigationView;
    ScrollView myScrollView;
    private DrawerLayout mdrawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);
       // myScrollView=findViewById(R.id.myScrollView);
        //hide scrollbar from ScrollView
      //  myScrollView.setVerticalScrollBarEnabled(false);
      //  myScrollView.setHorizontalScrollBarEnabled(false);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.animate().alpha(1).setDuration(300).setStartDelay(1600);
        setSupportActionBar(toolbar);
        mdrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle barDrawerToggle = new ActionBarDrawerToggle(this,mdrawerLayout,toolbar,R.string.navigaation_drawer_open,R.string.navigaation_drawer_close);
        mdrawerLayout.addDrawerListener(barDrawerToggle);
        barDrawerToggle.syncState();



        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.account);




        greetings = findViewById(R.id.greeting);
        username=findViewById(R.id.name);
        bgimage = findViewById(R.id.imagebg);
        bgimage.animate().translationY(-2100).setDuration(800).setStartDelay(800);
        greetings.animate().translationY(-1300).translationX(-180).setDuration(800).setStartDelay(800);
        username.animate().translationY(-1350).translationX(-180).setDuration(800).setStartDelay(800);




//        //Get the time of day
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//
//        //Set greeting
//        String greeting = null;
//        if(hour>= 12 && hour < 17){
//            greeting = "Good Afternoon";
//        } else if(hour >= 17 && hour < 21){
//            greeting = "Good Evening";
//        } else if(hour >= 21 && hour < 24){
//            greeting = "Good Night";
//        } else {
//            greeting = "Good Morning";
//        }


        DateFormat df = DateFormat.getTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        Calendar cal =   df.getCalendar();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        //Set greeting
        String greeting = null;
        if(hour>= 12 && hour < 17){
            greeting = "Good Afternoon";
        } else if(hour >= 17 && hour < 21){
            greeting = "Good Evening";
        } else if(hour >= 21 && hour < 24){
            greeting = "Good Night";
        } else {
            greeting = "Good Morning";
        }

        greetings.setText(greeting);

        username.setText(ParseUser.getCurrentUser().getUsername().toString());

    }
    Bid bid_fragment= new Bid();
    Details details_fragment = new Details();
    Support support_fragment = new Support();
    Wallet wallet_fragment = new Wallet();
    Account account_fragment = new Account();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_layout,account_fragment).commit();
                return true;
            case R.id.bid:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_layout,bid_fragment).commit();
                return true;
            case R.id.wallet:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_layout,wallet_fragment).commit();
                return true;

            case R.id.message:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_layout,support_fragment).commit();
                return true;
            case R.id.details:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_layout,details_fragment).commit();
                return true;

        }
        return false;
    }

    //close opened navigation drawer via back button
    @Override
    public void onBackPressed() {
        if (mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}
