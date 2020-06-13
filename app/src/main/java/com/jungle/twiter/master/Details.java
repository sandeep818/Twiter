package com.jungle.twiter.master;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.jungle.twiter.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details extends Fragment implements AdapterView.OnItemClickListener {
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


      arrayList = new ArrayList();
      arrayAdapter= new ArrayAdapter(getContext(),android.R.layout.simple_list_item_checked,arrayList);
      listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
      listView.setOnItemClickListener(this);


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
                           for (String tUser:arrayList){
                               if (ParseUser.getCurrentUser().getList("fanOf").contains(tUser)){
                                   listView.setItemChecked(arrayList.indexOf(tUser),true);
                               }
                           }
                       }
                   }
               }
           });
       }catch (Exception e){
           e.printStackTrace();
       }












      return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CheckedTextView checkedTextView =(CheckedTextView) view;
        if (checkedTextView.isChecked()){
            Toast.makeText(getContext(), arrayList.get(position) +" is fallowed", Toast.LENGTH_SHORT).show();

            ParseUser.getCurrentUser().add("fanOf",arrayList.get(position));
        }else{
            Toast.makeText(getContext(), arrayList.get(position) +" is Unfallowed", Toast.LENGTH_SHORT).show();
            ParseUser.getCurrentUser().getList("fanOf").remove(arrayList.get(position));
            List currentFanList = ParseUser.getCurrentUser().getList("fanOf");
            ParseUser.getCurrentUser().remove("fanOf");
            ParseUser.getCurrentUser().put("fanOf",currentFanList);
        }

        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){
                    Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
