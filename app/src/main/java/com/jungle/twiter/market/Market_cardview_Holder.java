package com.jungle.twiter.market;
import android.view.View;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jungle.twiter.R;
public class Market_cardview_Holder extends RecyclerView.ViewHolder {
    TextView market_title;
    public Market_cardview_Holder(@NonNull View itemView) {
        super(itemView);
        this.market_title = itemView.findViewById(R.id.market_title);
    }
}
