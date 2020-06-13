package com.jungle.twiter.market;
import android.view.View;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jungle.twiter.R;
public class Market_cardview_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView market_title,start_time,end_time;
    ItemClickListener itemClickListener;
    public Market_cardview_Holder(@NonNull View itemView) {
        super(itemView);
        this.market_title = itemView.findViewById(R.id.market_title);
        this.start_time= itemView.findViewById(R.id.start_time);
        this.end_time=itemView.findViewById(R.id.end_time);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v,getLayoutPosition());

    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener=ic;
    }
}
