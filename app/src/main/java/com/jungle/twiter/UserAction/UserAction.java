package com.jungle.twiter.UserAction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jungle.twiter.R;

public class UserAction extends AppCompatActivity {
    Bundle bundle;
    TextView fullName,user,creditt,exposert;
    LinearLayout listStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_action);
         bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String name = bundle.getString("name");
        String role = bundle.getString("role");
        String email = bundle.getString("email");
        String status = bundle.getString("status");
        String credit = bundle.getString("credit");
        String balance = bundle.getString("balance");
        String username = bundle.getString("username");

        fullName =(TextView)findViewById(R.id.list_name);
        user =(TextView)findViewById(R.id.list_user);
        creditt =(TextView)findViewById(R.id.list_credit);
        exposert =(TextView)findViewById(R.id.list_exposer);
        listStatus =(LinearLayout) findViewById(R.id.list_status);
        fullName.setText(name);
        user.setText("@"+username);
        creditt.setText("₹ "+credit);
        exposert.setText(email);
        if (status.equals("active")){
            listStatus.setBackgroundColor(getResources().getColor(R.color.pdlg_color_green));

        }else{
            listStatus.setBackgroundColor(getResources().getColor(R.color.pdlg_color_red));
        }

    }
}
