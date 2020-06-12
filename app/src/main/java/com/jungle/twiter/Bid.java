package com.jungle.twiter;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class Bid extends Fragment {

    EditText editText;
    Button post;
    Button view_Post;

    public Bid() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bid, container, false);

        editText = view.findViewById(R.id.edit_twit);
        post = view.findViewById(R.id.post);
        view_Post = view.findViewById(R.id.view_All);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().equals("")){
                    Toast.makeText(getContext(), " Please Enter Your Twit", Toast.LENGTH_SHORT).show();
                }else{
                    ParseObject parseObject = new ParseObject("My_twit");
                    parseObject.put("twit",editText.getText().toString());
                    parseObject.put("username", ParseUser.getCurrentUser().getUsername().toString());
                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e==null){
                                Toast.makeText(getContext(), "twit posted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });




    return view;
    }

}
