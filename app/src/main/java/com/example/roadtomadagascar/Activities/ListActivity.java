package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.roadtomadagascar.Adapters.ListAdapter;
import com.example.roadtomadagascar.Adapters.PlaceAdapter;
import com.example.roadtomadagascar.Adapters.PopularAdapter;
import com.example.roadtomadagascar.Dao.Dao;
import com.example.roadtomadagascar.Domains.CategoryDomain;
import com.example.roadtomadagascar.Domains.PlaceDomain;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private RecyclerView.Adapter listAdapter,adapterCat;
    private RecyclerView recyclerViewPopular,recyclerViewCategory;

    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        TextView actionTxt = findViewById(R.id.actionTxt);

        if(intent != null){
            String action = intent.getStringExtra("action");
            if (action != null) {
                switch (action) {
                    case "Lieux":
                        actionTxt.setText("Les lieux à Madagascar");
                        ArrayList<PlaceDomain> items = new ArrayList<>();

                        String url = "https://back-tourisme-git-main-matthieurt.vercel.app/touristspots/list"; // Remplacez par l'URL de votre API
                        RequestQueue queue = Volley.newRequestQueue(this);
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
                                                        singleObject.getBoolean("guide"),
                                                        singleObject.getInt("score"),
                                                        singleObject.getString("url"),
                                                        singleObject.getBoolean("isPopulaire"),
                                                        singleObject.getString("url")
                                                );
                                                items.add(p);
                                                listAdapter=new ListAdapter(items);
                                                recyclerViewPopular.setAdapter(listAdapter);
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
                        queue.add(stringRequest);
                        break;
                    case "Categorie":
                        CategoryDomain c = (CategoryDomain) intent.getSerializableExtra("object");
                        ImageView im = findViewById(R.id.imageView8);
                        im.setVisibility(View.VISIBLE);
                        int resourceId = getResources().getIdentifier(c.getTitle(), "drawable", getPackageName());
                        //im.setImageResource(resourceId);
                        actionTxt.setText(c.getTitle());

                         url = "https://back-tourisme-git-main-matthieurt.vercel.app/touristspots/categorie/"+c.getId(); // Remplacez par l'URL de votre API
                         queue = Volley.newRequestQueue(this);
                         stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        ArrayList<PlaceDomain> items = new ArrayList<PlaceDomain>();
                                        try {
                                            JSONArray array = new JSONArray(response);
                                            for(int i =0;i<array.length();i++){
                                                JSONObject singleObject = array.getJSONObject(i);
                                                PlaceDomain p = new PlaceDomain(singleObject.getString("_id"),
                                                        singleObject.getString("idCategorie"),
                                                        singleObject.getString("name"),
                                                        singleObject.getString("location"),
                                                        singleObject.getString("description"),
                                                        singleObject.getInt("distance"),
                                                        singleObject.getBoolean("guide"),
                                                        singleObject.getInt("score"),
                                                        singleObject.getString("url"),
                                                        singleObject.getBoolean("isPopulaire"),
                                                        singleObject.getString("url")
                                                );
                                                items.add(p);
                                            }
                                            listAdapter=new ListAdapter(items);
                                            recyclerViewPopular.setAdapter(listAdapter);

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
                        queue.add(stringRequest);
                         url = "https://back-tourisme-git-main-matthieurt.vercel.app/touristspots/categorie/"+c.getId(); // Remplacez par l'URL de votre API
                         queue = Volley.newRequestQueue(this);
                         stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        ArrayList<PlaceDomain> items = new ArrayList<PlaceDomain>();
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
                                                        singleObject.getBoolean("guide"),
                                                        singleObject.getInt("score"),
                                                        singleObject.getString("url"),
                                                        singleObject.getBoolean("isPopulaire"),
                                                        singleObject.getString("url")
                                                );
                                                items.add(p);
                                            }
                                            listAdapter=new ListAdapter(items);
                                            recyclerViewPopular.setAdapter(listAdapter);

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
                        queue.add(stringRequest);
                        break;
                    case  "recherche":
                        String query = intent.getStringExtra("object");
                        ImageView im1 = findViewById(R.id.imageView8);
                        im1.setVisibility(View.VISIBLE);
                        //im1.setImageResource(resourceId1);
                        actionTxt.setText("Recherche : "+query);

                        String url1 = "https://back-tourisme-git-main-matthieurt.vercel.app/touristspots/search/"+query; // Remplacez par l'URL de votre API
                        RequestQueue queue1 = Volley.newRequestQueue(this);
                        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        ArrayList<PlaceDomain> items = new ArrayList<PlaceDomain>();
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
                                                        singleObject.getBoolean("guide"),
                                                        singleObject.getInt("score"),
                                                        singleObject.getString("url"),
                                                        singleObject.getBoolean("isPopulaire"),
                                                        singleObject.getString("url")
                                                );
                                                items.add(p);
                                            }
                                            listAdapter=new ListAdapter(items);
                                            recyclerViewPopular.setAdapter(listAdapter);

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
                        queue1.add(stringRequest1);
                        break;
                    default:
                        actionTxt.setText("Unknown action.");
                        break;
                }
            }
        }

        ImageView backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerViewPopular= findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}