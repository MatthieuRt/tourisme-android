package com.example.roadtomadagascar.Dao;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.roadtomadagascar.Domains.CategoryDomain;
import com.example.roadtomadagascar.Domains.PlaceDomain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Dao {

    public ArrayList<PlaceDomain> getPlaceList(Context context) {
        String url = "https://back-tourisme-git-main-matthieurt.vercel.app/touristspots/list"; // Remplacez par l'URL de votre API
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        ArrayList<PlaceDomain> ret = new ArrayList<PlaceDomain>();
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: " + response);
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i =0;i<array.length();i++){
                                JSONObject singleObject = array.getJSONObject(i);
                                PlaceDomain p = new PlaceDomain(
                                        singleObject.getString("_id"),
                                        singleObject.getString("idCategorie"),
                                        singleObject.getString("name"),
                                        singleObject.getString("location"),
                                        singleObject.getString("description"),
                                        singleObject.getInt("distance"),
                                        singleObject.getBoolean("isPopulaire"),
                                        singleObject.getBoolean("guide"),
                                        singleObject.getInt("score"),
                                        "drawable/pic1"
                                );
                                ret.add(p);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
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
        return ret;
    }

    public ArrayList<CategoryDomain> getListeCategories(Context context){
        String url = "https://back-tourisme-git-main-matthieurt.vercel.app/categorie/list"; // Remplacez par l'URL de votre API
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        ArrayList<CategoryDomain> ret = new ArrayList<CategoryDomain>();
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: " + response);
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i =0;i<array.length();i++){
                                JSONObject singleObject = array.getJSONObject(i);
                                CategoryDomain p = new CategoryDomain(
                                    singleObject.getString("_id"),
                                    singleObject.getString("titre"),
                                    singleObject.getString("url")
                                );
                                ret.add(p);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
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
        return ret;
    }
}
