package com.example.divingproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyClusterItem implements ClusterItem {

    private LatLng position;
    private String title;
    private String snippet;

    public MyClusterItem(double lat, double lon, String title, String snippet){
        this.position = new LatLng(lat, lon);
        this.title = title;
        this.snippet = snippet;
    }

    @NonNull
    @Override
    public LatLng getPosition() {
        return position;
    }

    @Nullable
    @Override
    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public String getSnippet() {
        return snippet;
    }

    @Nullable
    @Override
    public Float getZIndex() {
        return null;
    }
}
