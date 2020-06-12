package com.jungle.twiter.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;

import com.jungle.twiter.R;
import com.jungle.twiter.market.Market_cardview_adapter;
import com.jungle.twiter.market.Market_model;

import java.util.ArrayList;

public class View_market extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Market_cardview_adapter cardview_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_market);
        mRecyclerView=findViewById(R.id.market_recycle);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        cardview_adapter = new Market_cardview_adapter(this,listdata());
        mRecyclerView.setAdapter(cardview_adapter);


    }
    private ArrayList<Market_model> listdata(){
        ArrayList<Market_model> market_models = new ArrayList<>();
        Market_model model = new Market_model();
        model.setId_n("as");
        model.setMarket_name("indore ");
        model.setStatus("Active - Premium");
        market_models.add(model);

        model = new Market_model();
        model.setId_n("as");
        model.setMarket_name("indore - ");
        model.setStatus("Active - Premium");
        market_models.add(model);

        return market_models;

    }
}

