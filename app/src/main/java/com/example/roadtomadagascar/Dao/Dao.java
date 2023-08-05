package com.example.roadtomadagascar.Dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class Dao {

    public void getData(Context context) {
        String url = "https://back-tourisme-git-main-matthieurt.vercel.app/touristspots/list"; // Remplacez par l'URL de votre API
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("THIS DIDINT WORK");
                error.printStackTrace();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
