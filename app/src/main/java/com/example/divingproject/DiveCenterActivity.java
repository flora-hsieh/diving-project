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
import com.android.volley.toolbox.JsonArrayRequest;


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
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class DiveCenterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    private ArrayList<DiveCenter> diveCenters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_dive_center);

        recyclerView = findViewById(R.id.diving_center_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the diveCenters list
        diveCenters = new ArrayList<>();
        adapter = new RecyclerAdapter(diveCenters, this);
        recyclerView.setAdapter(adapter);

        //Get data from MainActivity class
        Intent intent = getIntent();
        String searchLocation = intent.getStringExtra("searchLocationData");
        fetchDiveCenterData(searchLocation);
    }

    private void fetchDiveCenterData(String searchLocation) {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("https://world-dive-centres-api.p.rapidapi.com/api/divecentre?country=%s",searchLocation);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", "e01c1d21d0msh2c15fc311674ae2p13d58ajsnb61302321d40")
                .addHeader("x-rapidapi-host", "world-dive-centres-api.p.rapidapi.com")
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
                            JSONObject diveCenter = dataArray.getJSONObject(i);
                            String name = diveCenter.getString("name");
                            String location = diveCenter.getString("location");
                            String type = diveCenter.getString("type");
                            String number = diveCenter.getString("number");

                            diveCenters.add(new DiveCenter(name, number, location, type));
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
            public void onFailure(Call call, IOException e) {
                Log.e("OkHttp", "Error fetching data: " + e.getMessage());
            }



        });
    }
}
