package com.atabegruslan.request.demo1.getapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class GetActivity extends AppCompatActivity {

    private JSONObject jsonResponse;
    private String URL = "http://ip-api.com/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            jsonResponse = new JSONObject(response);
                            String city = jsonResponse.optString("city");
                            String timezone = jsonResponse.optString("timezone");
                            String lat = jsonResponse.optString("lat");
                            String lon = jsonResponse.optString("lon");
                            String result = city + " : " + timezone + " (  Lat: " + lat + " / Long:  " + lon + " ) ";
                            Toast.makeText( GetActivity.this.getApplicationContext(), result, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response is", "That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
}
