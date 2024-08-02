package com.example.divingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class DiveCenter {


    private String centerName;
    private String centerLicense;
    private String centerLocation;
    private String centerType;

    public DiveCenter(String centerName, String centerLicense, String centerLocation, String centerType){
        this.centerName = centerName;
        this.centerLicense = centerLicense;
        this.centerLocation = centerLocation;
        this.centerType = centerType;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterLicense() {
        return centerLicense;
    }

    public void setCenterLicense(String centerLicense) {
        this.centerLicense = centerLicense;
    }

    public String getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(String centerLocation) {
        this.centerLocation = centerLocation;
    }

    public String getCenterType() {
        return centerType;
    }

    public void setCenterType(String centerType) {
        this.centerType = centerType;
    }
}
