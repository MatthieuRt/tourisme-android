package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.roadtomadagascar.Adapters.ListAdapter;
import com.example.roadtomadagascar.Domains.PlaceDomain;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import util.SessionUser;

public class FavorisActivity extends AppCompatActivity {

    private RecyclerView.Adapter listAdapter,adapterCat;
    private RecyclerView recyclerViewPopular,recyclerViewCategory;

    private ImageView backBtn;
    private List<PlaceDomain> listFavoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        ImageView backBtn = findViewById(R.id.backBtn);
        Intent intent = getIntent();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayList<PopularDomain> items = new ArrayList<>();
        //items.add(new PopularDomain("Plage d'Antsanitia","Majunga","Ceci est une description",2,true,4.8,"pic1",true,1000));
        //items.add(new PopularDomain("Allée des Baobabs","Morondava","Ceci est une description",1,false,5,"pic2",false,2500));
        //items.add(new PopularDomain("Foulpointe","Foulpointe","Ceci est une description",3,true,4.8,"pic1",true,1000));

        String url = "https://back-tourisme-git-main-matthieurt.vercel.app/touristspots/list";
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        SessionUser sessionUser = SessionUser.getSessionUser();
        List<String> listFavorisUser = sessionUser.getListFavoris();
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Process the response from the server
                        try {
                            JSONArray repList = new JSONArray(response);
                            for (int i = 0; i < repList.length(); i++) {
                                JSONObject singleObject = repList.getJSONObject(i);
                                if(listFavorisUser.contains(singleObject.getString("_id"))){
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
                                            "pic1"
                                    );
                                    System.out.println(p);
                                    listFavoris.add(p);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("THIS DIDINT WORK");
                error.printStackTrace();
            }
        }) {
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        recyclerViewPopular= findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listAdapter=new ListAdapter(listFavoris);
        recyclerViewPopular.setAdapter(listAdapter);
    }
}