package com.example.ad340project;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class trafficDetail extends AppCompatActivity {

    ListView cameraList;
    CameraListAdapter listAdapter;
    String dataUrl = "http://brisksoft.us/ad340/traffic_cameras_merged.json";
    ArrayList<traffic> cameraData = new ArrayList<>();
    public static final String TAG = "NETWORK";
    private static boolean wifiConnected = false;
    private static boolean mobileConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cameraList = findViewById(R.id.trafficLayout);
        listAdapter = new CameraListAdapter(this, cameraData);
        cameraList.setAdapter(listAdapter);
        checkNetworkConnection();
        if(wifiConnected || mobileConnected) {
            loadCameraData(dataUrl);
        } else {
            //show toast
        }
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
                        traffic c = new traffic(
                                camera.getString("cameralabel"),
                                camera.getString("ownershipcd"),
                                camera.getJSONObject("imageurl").getString("url"),
                                camera.getString("ypos"),
                                camera.getString("xpos")
                        );
                        cameraData.add(c);
                    }
                    listAdapter.notifyDataSetChanged();
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

    public class CameraListAdapter extends ArrayAdapter<traffic> {

        private final Context context;
        private ArrayList<traffic> values;

        public CameraListAdapter(Context context, ArrayList<traffic> values) {
            super(context, 0, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public int getCount() {
            return values.size();
        }

        @Override
        public traffic getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.content_traffic, parent, false);
            TextView label = rowView.findViewById(R.id.textView_label);
            TextView y = rowView.findViewById(R.id.textView_y);
            TextView x = rowView.findViewById(R.id.textView_x);
            ImageView image = rowView.findViewById(R.id.imageView_traffic);
            label.setText(values.get(position).label);
            y.setText(values.get(position).y);
            x.setText(values.get(position).x);
            String imageUrl = values.get(position).image;
            if(!imageUrl.isEmpty()) {
                Picasso p = Picasso.get();
                p.load(imageUrl).into(image);
            }
            return rowView;
        }
    }

    public void checkNetworkConnection() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if(wifiConnected) {
                Log.i(TAG, "WIFI connection");
            } else if (mobileConnected){
                Log.i(TAG, "Mobile connection");
            }
        } else {
            Log.i(TAG, "No network connection");
        }
    }


}
