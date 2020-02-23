package com.jungle.twiter;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details extends Fragment {
 private ArrayList<String> arrayList;
 ListView listView;
 ArrayAdapter arrayAdapter;

    public Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =inflater.inflate(R.layout.fragment_details, container, false);
      listView = view.findViewById(R.id.list_view);

      arrayList = new ArrayList();
      arrayAdapter= new ArrayAdapter(getContext(),android.R.layout.simple_list_item_checked,arrayList);
      listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);


       try {
           ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
           parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername().toString());
           parseQuery.findInBackground(new FindCallback<ParseUser>() {
               @Override
               public void done(List<ParseUser> objects, ParseException e) {

                   if (e==null) {
                       if (objects.size() > 0) {
                           for (ParseUser user : objects) {
                               arrayList.add(user.getUsername().toString());
                           }
                           listView.setAdapter(arrayAdapter);
                       }
                   }
               }
           });
       }catch (Exception e){
           e.printStackTrace();
       }












      return view;
    }

}
