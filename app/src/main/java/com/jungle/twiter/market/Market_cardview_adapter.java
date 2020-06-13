package com.jungle.twiter.market;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jungle.twiter.R;
import com.jungle.twiter.admin.Market_details;

import java.util.ArrayList;

public class Market_cardview_adapter extends RecyclerView.Adapter<Market_cardview_Holder> {
Context context;
ArrayList<Market_model> market_model;

    public Market_cardview_adapter(Context context, ArrayList<Market_model> market_model) {
        this.context = context;
        this.market_model = market_model;
    }

    @NonNull
    @Override
    public Market_cardview_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_cardview,null);
        return new Market_cardview_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Market_cardview_Holder holder, int position) {
        holder.market_title.setText(market_model.get(position).getMarket_name());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
              String titleMarket =  market_model.get(position).getMarket_name();
                Intent intent =new Intent(context, Market_details.class);
                intent.putExtra("market",titleMarket);
               context.startActivities(new Intent[]{intent});


            }
        });

    }

    @Override
    public int getItemCount() {
        return market_model.size();
    }
}
