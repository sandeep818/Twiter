package com.jungle.twiter.getAllUser;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;
import com.jungle.twiter.R;
import com.jungle.twiter.Support;

import java.util.ArrayList;

public class UserlistAdapter extends BaseAdapter implements View.OnClickListener, Filterable {
    public ArrayList<UserAllDataModel> displayData;
    Context mcontext;
    public ArrayList<UserAllDataModel> currentUserData;
    FilterHelper filterHelper;

    public UserlistAdapter(ArrayList<UserAllDataModel> displayData, Context mcontext) {

        this.displayData = displayData;
        this.mcontext = mcontext;
        this.currentUserData=displayData;
    }

    @Override
    public int getCount() {
        return displayData.size();
    }

    @Override
    public Object getItem(int position) {
        return displayData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=LayoutInflater.from(mcontext).inflate(R.layout.row_item,parent,false);
        }
        Animation animation = AnimationUtils.loadAnimation(mcontext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        convertView.startAnimation(animation);
        lastPosition = position;
         TextView txtName = (TextView) convertView.findViewById(R.id.User_name);
        TextView txtstatus = (TextView) convertView.findViewById(R.id.status);
        TextView txtcredit = (TextView) convertView.findViewById(R.id.credit);
        ImageView usertype = (ImageView) convertView.findViewById(R.id.user_type);

        final UserAllDataModel dataModel = (UserAllDataModel) this.getItem(position);

        txtName.setText(dataModel.getName());
        txtstatus.setText(dataModel.getStatus());
        txtcredit.setText(dataModel.getCredit());
        usertype.setImageResource(R.drawable.mastericon);




        return convertView;
    }



    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ////////////////////////////////////////////////////////////////////

        UserAllDataModel dataModel=(UserAllDataModel) object;

        switch (v.getId())
        {
            case R.id.user_type:
                Snackbar.make(v, "oky.... " +dataModel.getName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }


    }

    public void setUserList(ArrayList<UserAllDataModel> filteredUserList ){
//        Log.d("serach", "performFiltering: "+filteredUserList.get(0).getName() );
       this.displayData=filteredUserList;

    }


    @Override
    public Filter getFilter() {
        if (filterHelper==null){
            filterHelper = new FilterHelper(this.currentUserData,this,mcontext);
        }
        return filterHelper;
    }

    public void refresh(){
        notifyDataSetChanged();
    }
}
