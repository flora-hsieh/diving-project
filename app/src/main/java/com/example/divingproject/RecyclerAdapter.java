package com.example.divingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<DiveCenter> diveCenters;
    private Context context;

    public RecyclerAdapter(ArrayList<DiveCenter> diveCenters, Context context) {
        this.diveCenters = diveCenters;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View diveCenterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dive_center, parent, false);
        return new ViewHolder(diveCenterView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        DiveCenter diveCenter = diveCenters.get(position);
        holder.center_name.setText(diveCenter.getCenterName());
        holder.center_location.setText(diveCenter.getCenterLocation());
        holder.center_type.setText(diveCenter.getCenterType());

        if (diveCenter.getCenterType().toLowerCase().contains("padi")) {
            holder.license_logo.setImageResource(R.drawable.logo_of_padi);
        } else if (diveCenter.getCenterType().toLowerCase().contains("sdi")){
            holder.license_logo.setImageResource(R.drawable.logo_of_sdi);
        } else {
            holder.license_logo.setImageResource(R.drawable.logo_of_ssi);
        }
    }

    @Override
    public int getItemCount() {
        return diveCenters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView license_logo;
        TextView center_name, center_location, center_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            license_logo = itemView.findViewById(R.id.license_logo);
            center_name = itemView.findViewById(R.id.center_name);
            center_location = itemView.findViewById(R.id.center_location);
            center_type = itemView.findViewById(R.id.center_type);
        }
    }
}