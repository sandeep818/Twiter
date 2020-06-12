package com.jungle.twiter.getAllUser;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;
import android.widget.Toast;

import com.jungle.twiter.Support;

import java.util.ArrayList;

public class FilterHelper extends Filter  {
    ArrayList<UserAllDataModel> userDataList;
    UserlistAdapter userlistAdapter;
    Context context;

    public FilterHelper(ArrayList<UserAllDataModel> userDataList, UserlistAdapter userlistAdapter, Context context) {
        this.userDataList = userDataList;
        this.userlistAdapter = userlistAdapter;
        this.context = context;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
       FilterResults filterResults =new FilterResults();
       if (constraint != null && constraint.length() >0){

           constraint = constraint.toString().toUpperCase();

           ArrayList<UserAllDataModel> foundFilters=new ArrayList<>();
           UserAllDataModel userAllDataModel=null;

           for (int i=0;i<userDataList.size();i++){

               userAllDataModel = userDataList.get(i);
               if(userAllDataModel.getName().toUpperCase().contains(constraint)){
                   Log.d("serach", "performFiltering:"+userAllDataModel.getName());
                   foundFilters.add(userAllDataModel);
               }
           }
           filterResults.count=foundFilters.size();
           filterResults.values=foundFilters;
       }else {
           filterResults.count=userDataList.size();
           filterResults.values=userDataList;

       }


        return filterResults;
    }




    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        Log.d("serach", "performFiltering: "+results.count + " "+ userDataList.size());
        userlistAdapter.setUserList((ArrayList<UserAllDataModel>) results.values);
        userlistAdapter.refresh();

    }
}
