package com.gsonvolley.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import android.net.NetworkInfo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private EarthquakeAdapter mAdapter;

    List<featuredata> mfeatureData;

    

    private TextView mEmptyTextView;
    private String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&limit=6&minmagnitude=4";
   

    private RecyclerView earthquakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mEmptyTextView = (TextView)findViewById(R.id.empty_view);
        //View loadingIndicator = findViewById(R.id.loading_indicator);
       

        mfeatureData = new ArrayList<>();


       earthquakeView = (RecyclerView) findViewById(R.id.list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        earthquakeView.setLayoutManager(layoutManager);


        earthquakeView.setHasFixedSize(true);

    }

    
    @Override
    protected void onStart() {
        super.onStart();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

    ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("akunamatata", response.toString());

                            Gson gson = new Gson();
                            Earthquakedatajsonclass red = gson.fromJson(response.toString(), Earthquakedatajsonclass.class);

                            for(int i=0;i<red.getFeatures().size();i++){
                                mfeatureData.add(red.getFeatures().get(i));
                            }

                            Log.d("error",mfeatureData.toString());



                        }


                        //mEarthquakedatajsonclass = red;

                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Auto-generated method stub

                        }
                    });

            requestQueue.add(jsObjRequest);

            mAdapter = new EarthquakeAdapter(mfeatureData,DataActivity.this);

            earthquakeView.setAdapter(mAdapter);
           // loadingIndicator.setVisibility(View.GONE);





        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible

            //loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyTextView.setText(R.string.no_internet_connection);
        }



// Access the RequestQueue through your singleton class.


    }
}



