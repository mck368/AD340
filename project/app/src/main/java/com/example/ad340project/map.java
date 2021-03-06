package com.example.ad340project;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class map extends FragmentActivity implements OnMapReadyCallback {

    Location currentLocation;
    private static final int REQUEST_CODE = 9;
    private FusedLocationProviderClient client;
    String dataUrl = "http://brisksoft.us/ad340/traffic_cameras_merged.json";
    public static final String TAG = "NETWORK";
    private static boolean wifiConnected = false;
    private static boolean mobileConnected = false;
    private ArrayList<LatLng> latLngs = new ArrayList<>();
    private ArrayList<String> label = new ArrayList<>();
    private ArrayList<String> owner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        loadCameraData(dataUrl);
        client = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(map.this,
                ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        client.getLastLocation().addOnSuccessListener(map.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    SupportMapFragment supportMapFragment = (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(map.this);
                }
            }

        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions markCams = new MarkerOptions();
        for (int i = 0; i < latLngs.size(); i++) {
            markCams.position(latLngs.get(i));
            markCams.title(label.get(i));
            markCams.snippet(owner.get(i));
            googleMap.addMarker(markCams);
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLngs.get(i)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngs.get(i), 12));
        }

        LatLng currLocation = new LatLng(47.6733, -122.3426);
        MarkerOptions markCurr = new MarkerOptions().position(currLocation).title("I am here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(currLocation));
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLocation, 9));
        googleMap.addMarker(markCurr);
    }

    public void loadCameraData(String dataUrl) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, dataUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("CAMERAS 1", response.toString());
                try {
                    for (int i = 1; i < response.length(); i++) {
                        JSONObject camera = response.getJSONObject(i);
                        camera.getDouble("ypos");
                        camera.getDouble("xpos");
                        camera.getString("cameralabel");
                        camera.getString("ownershipcd");
                        latLngs.add(new LatLng(camera.getDouble("ypos"), camera.getDouble("xpos")));
                        label.add(camera.getString("cameralabel"));
                        owner.add(camera.getString("ownershipcd"));
                    }
                } catch (JSONException e) {
                    Log.d("CAMERAS error", e.getMessage());
                }
            }
        }, (new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSON", "Error: " + error.getMessage());
            }
        }));
        queue.add(jsonReq);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }
    }


}
