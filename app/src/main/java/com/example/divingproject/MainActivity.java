package com.example.divingproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.io.IOException;
import java.util.ArrayList;
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
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //SearchView
    private SearchView mapSearchView;
    //Google Map
    private GoogleMap myMap;

    //Fetch user location variables
    private final int FINE_PERMISSION_CODE = 1;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    public LocationManager locationManager;
    String bestProvider;

    //Cluster Markers
    private ClusterManager<MyClusterItem> clusterManager;

    public String searchLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fetch user location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(MainActivity.this);


        //SearchView
        mapSearchView = findViewById(R.id.mapSearch);
        //assert mapFragment != null;
        //mapFragment.getMapAsync(MainActivity.this);
        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = mapSearchView.getQuery().toString();
                searchLocation = mapSearchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null) {
                    Geocoder geocoder = new Geocoder(MainActivity.this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    Address address = addressList.get(0);
                    LatLng userSearch = new LatLng(address.getLatitude(), address.getLongitude());
                    MarkerOptions options = new MarkerOptions().position(userSearch).title(location);
                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userSearch, 3));
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                    myMap.addMarker(options);
                    fetchDiveSiteData(searchLocation);
                }


                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);

                return false;
            }

        });


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();


    }


    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MainActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        myMap = googleMap;
        //Cluster Markers Setting
        clusterManager = new ClusterManager<MyClusterItem>(this, myMap);
        myMap.setOnCameraIdleListener(clusterManager);


        //Pin user's current location according to the device location
        LatLng user = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        myMap.moveCamera(CameraUpdateFactory.newLatLng(user));
        MarkerOptions options = new MarkerOptions().position(user).title("My Location").snippet("This is my device location");
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        myMap.addMarker(options);

        //Enable Zoom & Compass control
        myMap.getUiSettings().setZoomControlsEnabled(true);


    }

    //Request device location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Location permission is denied, please allow the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private ArrayList<DiveSite> diveSites; // Declare the list

    //API parameters
    private static final String DIVESITE_API_KEY = "e01c1d21d0msh2c15fc311674ae2p13d58ajsnb61302321d40";
    private static final String DIVESITE_API_HOST = "world-scuba-diving-sites-api.p.rapidapi.com";

    int listLength;
    //original code
    private void fetchDiveSiteData(String searchLocation) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://world-scuba-diving-sites-api.p.rapidapi.com/api/divesite?country=%s", searchLocation))
                .get()
                .addHeader("x-rapidapi-key", DIVESITE_API_KEY)
                .addHeader("x-rapidapi-host", DIVESITE_API_HOST)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("API Failure", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONArray dataArray = jsonObject.getJSONArray("data");
                        listLength = dataArray.length();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    LatLng latituteLontitude = new LatLng(0, 0);
                                    diveSites = new ArrayList<>();
                                    for (int i = 0; i < dataArray.length(); i++) {
                                        JSONObject diveSite = dataArray.getJSONObject(i);

                                        //parameters for clusterItems
                                        String name = diveSite.getString("name");
                                        String region = diveSite.getString("region");
                                        int latitude = diveSite.getInt("lat");
                                        int longitude = diveSite.getInt("lng");
                                        latituteLontitude = new LatLng(latitude, longitude);
                                        //add markers
                                        myMap.addMarker(new MarkerOptions().position(latituteLontitude));
                                        MyClusterItem offsetItem = new MyClusterItem(latitude, longitude, name, region);
                                        clusterManager.addItem(offsetItem);

                                        //parameters for divesite details
//                                        String siteName = diveSite.getString("siteName");
//                                        String siteRegion = diveSite.getString("siteRegion");
//                                        String siteOcean = diveSite.getString("siteOcean");
//                                        String siteLocation = diveSite.getString("siteLocation");
//                                        diveSites.add(new DiveSite(null, siteName, siteRegion, siteOcean, siteLocation));

                                    }
                                    myMap.moveCamera(CameraUpdateFactory.newLatLng(latituteLontitude));
                                } catch (Exception e) {
                                    Log.e("JSON Parsing Error", e.getMessage());
                                }
                            }
                        });
                    } catch (Exception e) {
                        Log.e("JSON Parsing Error", e.getMessage());
                    }
                } else {
                    Log.e("API Error", response.message());
                }
            }
        });


    }



    //Call the Rapid API to get the diving spots from all around the world by using country parameter
//    private void fetchDiveSiteData(String searchLocation) {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(String.format("https://world-scuba-diving-sites-api.p.rapidapi.com/api/divesite?country=%s", searchLocation))
//                .get()
//                .addHeader("x-rapidapi-key", DIVESITE_API_KEY)
//                .addHeader("x-rapidapi-host", DIVESITE_API_HOST)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("API Failure", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    final String responseData = response.body().string();
//                    try {
//                        JSONObject jsonObject = new JSONObject(responseData);
//                        JSONArray dataArray = jsonObject.getJSONArray("data");
//                        listLength = dataArray.length();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    LatLng latLng = new LatLng(0, 0);
//                                    diveSites = new ArrayList<>();
//                                    for (int i = 0; i < dataArray.length(); i++) {
//                                        JSONObject diveSite = dataArray.getJSONObject(i);
//                                        String photo = diveSite.getString("photo");
//                                        String name = diveSite.getString("name");
//                                        String region = diveSite.getString("region");
//                                        String ocean = diveSite.getString("ocean");
//                                        String location = diveSite.getString("location");
//                                        int latitude = diveSite.getInt("lat");
//                                        int longitude = diveSite.getInt("lng");
//                                        latLng = new LatLng(latitude, longitude);
//
//                                        diveSites.add(new DiveSite(photo, name, region, ocean, location));
//
//                                        //add marker
//                                        myMap.addMarker(new MarkerOptions().position(latLng));
//                                        MyClusterItem offsetItem = new MyClusterItem(latitude, longitude, name, region);
//                                        clusterManager.addItem(offsetItem);
//                                    }
//                                    myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                                } catch (Exception e) {
//                                    Log.e("JSON Parsing Error", e.getMessage());
//                                }
//                            }
//                        });
//                    } catch (Exception e) {
//                        Log.e("JSON Parsing Error", e.getMessage());
//                    }
//                } else {
//                    Log.e("API Error", response.message());
//                }
//            }
//        });
//    }



    //Actions when you click on the "Dive Centers Details" Button
    public void openDiveCenterList(View view) {
        Intent intent = new Intent(this, DiveCenterActivity.class);
        intent.putExtra("searchLocationData", searchLocation);
        startActivity(intent);
    }

    //Actions when you click on the "Dive Sites Details" Button
    public void openDiveSiteList(View view) {
        Intent intent = new Intent(this, DiveSiteActivity.class);
        intent.putExtra("searchLocationData", searchLocation);
        intent.putExtra("listLength", listLength);
        startActivity(intent);
    }

    public void openHealthRecommendation(View view) {
        Intent intent = new Intent(this, HealthRecommendation.class);
        startActivity(intent);
    }
}





