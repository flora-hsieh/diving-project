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
import java.util.List;
import java.util.Random;

public class DiveSiteAdapter extends RecyclerView.Adapter<DiveSiteAdapter.DiveSiteViewHolder> {

    private ArrayList<DiveSite> diveSites;
    private Context context;

    Random random;

    public DiveSiteAdapter(ArrayList<DiveSite> diveSites, Context context) {
        this.diveSites = diveSites;
        this.context = context;
        this.random = new Random();
    }

    @NonNull
    @Override
    public DiveSiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View diveSiteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dive_site, parent, false);
        return new DiveSiteViewHolder(diveSiteView);
    }

    @Override
    public void onBindViewHolder(@NonNull DiveSiteAdapter.DiveSiteViewHolder holder, int position) {
        DiveSite diveSite = diveSites.get(position);
        holder.site_name.setText(diveSite.getSiteName());
        holder.site_region.setText(diveSite.getSiteRegion());
        holder.site_ocean.setText(diveSite.getSiteOcean());
        holder.site_location.setText(diveSite.getSiteLocation());

        int[] scubaDivingImages = {
                R.drawable.scubadiving01, R.drawable.scubadiving02, R.drawable.scubadiving03,
                R.drawable.scubadiving04, R.drawable.scubadiving05, R.drawable.scubadiving06,
                R.drawable.scubadiving07, R.drawable.scubadiving08, R.drawable.scubadiving09,
                R.drawable.scubadiving10, R.drawable.scubadiving11, R.drawable.scubadiving12,
                R.drawable.scubadiving13, R.drawable.scubadiving14, R.drawable.scubadiving15,
                R.drawable.scubadiving16, R.drawable.scubadiving17, R.drawable.scubadiving18,
                R.drawable.scubadiving19, R.drawable.scubadiving20};

        // Randomly select a scuba diving image
        int randomIndex = random.nextInt(scubaDivingImages.length);
        int randomImage = scubaDivingImages[randomIndex];

        // Set the random scuba diving image as the resource
        holder.site_photo.setImageResource(randomImage);
    }

    @Override
    public int getItemCount() {
        return diveSites.size();
    }

    public class DiveSiteViewHolder extends RecyclerView.ViewHolder {

        ImageView site_photo;
        TextView site_name, site_region, site_ocean, site_location;


        public DiveSiteViewHolder(@NonNull View itemView) {
            super(itemView);
            site_photo = itemView.findViewById(R.id.site_photo);
            site_name = itemView.findViewById(R.id.site_name);
            site_region = itemView.findViewById(R.id.site_region);
            site_ocean = itemView.findViewById(R.id.site_ocean);
            site_location = itemView.findViewById(R.id.site_location);

        }


    }
}
