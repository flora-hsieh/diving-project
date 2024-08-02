package com.example.divingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;


public class DiveSite {
    private String siteName;
    private String siteRegion;
    private String siteOcean;
    private String siteLocation;

    // Constructor
    public DiveSite(String siteName, String siteRegion, String siteOcean, String siteLocation) {
        this.siteName = siteName;
        this.siteRegion = siteRegion;
        this.siteOcean = siteOcean;
        this.siteLocation = siteLocation;
    }


    // Getters and setters

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteRegion() {
        return siteRegion;
    }

    public void setSiteRegion(String siteRegion) {
        this.siteRegion = siteRegion;
    }

    public String getSiteOcean() {
        return siteOcean;
    }

    public void setSiteOcean(String siteOcean) {
        this.siteOcean = siteOcean;
    }

    public String getSiteLocation() {
        return siteLocation;
    }

    public void setSiteLocation(String siteLocation) {
        this.siteLocation = siteLocation;
    }
}
