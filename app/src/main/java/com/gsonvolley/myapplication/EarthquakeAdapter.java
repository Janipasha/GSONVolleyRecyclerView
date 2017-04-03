package com.gsonvolley.myapplication;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.offset;
import static android.media.CamcorderProfile.get;
import static com.gsonvolley.myapplication.R.id.date;
import static com.gsonvolley.myapplication.R.id.magnitude;
import static com.gsonvolley.myapplication.R.id.submenuarrow;
import static java.lang.System.getProperties;

/**
 * Created by Jani on 31-03-2017.
 */

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.DataViewHolder> {

    private List<featuredata> FeatureDataList;
    private Context context;

    public class DataViewHolder extends RecyclerView.ViewHolder {

         public TextView magnitudeView, locationoffsetView, primaryLocation , dateView, timeView;

        public DataViewHolder(View itemView) {
            super(itemView);

            magnitudeView =(TextView) itemView.findViewById(magnitude);
            locationoffsetView = (TextView)itemView.findViewById(R.id.location_offset);
            primaryLocation= (TextView)itemView.findViewById(R.id.primary_location);
            dateView = (TextView)itemView.findViewById(date);
            timeView= (TextView)itemView.findViewById(R.id.time);
        }
    }

    public EarthquakeAdapter(List<featuredata> FeatureDataList, Context context) {
        this.FeatureDataList = FeatureDataList;
        this.context = context;
    }
    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.earthquakelistitem,parent,false);

        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        featuredata FeatureData = FeatureDataList.get(position);

       long Time = FeatureData.getProperties().getTime();

        /*Date dateObject = new Date(Time);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM DD,yyyy",java.util.Locale.getDefault());



        String date = dateFormat.format(dateObject);

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a",java.util.Locale.getDefault());

        String time = dateFormat.format(timeFormat);*/

        String date = "08 22,2015";
        String time = "8:06";


        Float mag = FeatureData.getProperties().getMag();

        String place =FeatureData.getProperties().getPlace();

        String location_offset , primary_location;

        if(place.contains("of")){
            String[] parts = place.split("of");

            location_offset = parts[0]+"of";

            primary_location = parts[1];


        }else {
            location_offset = "near the ";
            primary_location = place;
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String Magnitude = decimalFormat.format(mag);

       holder.timeView.setText(time);
        holder.dateView.setText(date);
        holder.primaryLocation.setText(primary_location);
        holder.locationoffsetView.setText(location_offset);
        holder.magnitudeView.setText(Magnitude);


    }



    @Override
    public int getItemCount() {

        return FeatureDataList.size();
    }
}
