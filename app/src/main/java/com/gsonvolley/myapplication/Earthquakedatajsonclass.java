package com.gsonvolley.myapplication;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import static android.R.attr.id;
import static android.R.attr.type;

/**
 * Created by Jani on 30-03-2017.
 */
public class Earthquakedatajsonclass{

    String type;
    Metadata metadata;
    List<featuredata> features;

    public String Gettype(){
        return type;
    }

    public List<featuredata> getFeatures(){
        return  features;
    }

    public Metadata getMetadata(){
        return metadata;
    }

}




