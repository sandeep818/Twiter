package com.jungle.twiter.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jungle.twiter.R;
import com.jungle.twiter.market.Get_market;
import com.jungle.twiter.market.Market_cardview_adapter;
import com.jungle.twiter.market.Market_model;
import com.jungle.twiter.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class View_market extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Market_cardview_adapter cardview_adapter;
    Get_market myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ArrayList<Market_model> marketModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_market);
        mRecyclerView=findViewById(R.id.market_recycle);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        marketModels = new MarketApi(getApplicationContext()).retrive();



    }
    private ArrayList<Market_model> listdata(){
        ArrayList<Market_model> market_models = new ArrayList<>();
        Market_model model = new Market_model("ass","asas","46","asas","asa");
        market_models.add(model);
        Market_model model1 = new Market_model("ass","asas","46","asas","asa");
        market_models.add(model1);

        marketModels = new MarketApi(getApplicationContext()).retrive();
        Log.d("oks", "listdata: "+marketModels.toString());
        Log.d("oks", "another OBJ: "+market_models.toString());
        return marketModels;

    }


    public class MarketApi{
        Context context;

        public MarketApi(Context context) {
            this.context = context;
        }


        // call api and get in array
        public ArrayList<Market_model> retrive(){
            ArrayList<Market_model> mdownloadData= new ArrayList<>();
            final  ArrayList<Market_model> mData=new ArrayList<>();

            Retrofit retrofit = RetrofitClient.getInstance();
            myAPI = retrofit.create(Get_market.class);
            compositeDisposable.add(myAPI.getMarket()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Throwable {
                            JSONArray arr = new JSONArray(s);
                            Market_model mdataModel;
                            try {
                                for(int i =0;i<arr.length(); i++){
                                    JSONObject jObj = arr.getJSONObject(i);
                                    String id= jObj.getString("id");
                                    String marketName = jObj.getString("market");
                                    String startTime = jObj.getString("opning_time");
                                    String endTime = jObj.getString("closing_time");

                                    String status = jObj.getString("status");

                                   // mdataModel=new Market_model("1","sagar","master","active","5000+");
                                    mdataModel=new Market_model(marketName,status,id,startTime,endTime);
                                    mdownloadData.add(mdataModel);
                                    Toast.makeText(View_market.this, "You Have "+arr.length()+" Markets", Toast.LENGTH_SHORT).show();

                                }


                            }catch (JSONException e){
                                Toast.makeText(View_market.this, "Y444444444444 Have "+arr.length()+" Markets", Toast.LENGTH_LONG).show();
                            }


                            Log.d("oks", "accept: "+mdownloadData);
                            getMarket(mdownloadData);



                        }

                    }));


            return mdownloadData;

        }
    }

public void getMarket(ArrayList<Market_model> market_models){
        cardview_adapter = new Market_cardview_adapter(this,market_models);
        mRecyclerView.setAdapter(cardview_adapter);

    }
}

