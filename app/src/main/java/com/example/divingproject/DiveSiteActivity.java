package com.example.divingproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.util.List;

import kotlin.LateinitKt;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;






public class DiveSiteActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private RequestQueue requestQueue;
    private ArrayList<DiveSite> diveSites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_dive_site);

        recyclerView = findViewById(R.id.diving_site_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        // Initialize the diveSites list
        diveSites = new ArrayList<>();
        adapter = new DiveSiteAdapter(diveSites, this);
        recyclerView.setAdapter(adapter);


        // Get data from MainActivity class
        Intent intent = getIntent();
        String searchLocation = intent.getStringExtra("searchLocationData");
        int listLength = intent.getIntExtra("listLength", 0);

        fetchDiveSiteList(searchLocation);
        //get picture from pixabay
//      fetchPicture(listLength);
    }



    private void fetchDiveSiteList(String searchLocation) {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("https://world-scuba-diving-sites-api.p.rapidapi.com/api/divesite?country=%s", searchLocation);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", "e3d0d68ee4mshc5a694b8cf6541bp113b12jsn51427d6a94e2")
                .addHeader("x-rapidapi-host", "world-scuba-diving-sites-api.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        JSONArray dataArray = jsonResponse.getJSONArray("data");

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject diveSite = dataArray.getJSONObject(i);
                            String name = diveSite.getString("name");
                            String region = diveSite.getString("region");
                            String ocean = diveSite.getString("ocean");
                            String location = diveSite.getString("Location");

                            diveSites.add(new DiveSite(name, region, ocean, location));
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });

                    } catch (JSONException e) {
                        Log.e("OkHttp", "Error parsing JSON: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("OkHttp", "Error fetching data: " + e.getMessage());
            }
        });
    }
}