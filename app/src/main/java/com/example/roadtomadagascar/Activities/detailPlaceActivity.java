package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import util.SessionUser;

public class detailPlaceActivity extends AppCompatActivity {

    private TextView titleTxt,locationTxt,guideTxt,descriptionTxt,scoreTxt;
    private PopularDomain item;
    private ImageView picImg, backBtn;
    private ImageView addFavoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        setVariable();
        initView();
    }

    private void setVariable(){
        item = (PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        scoreTxt.setText(""+(int) item.getScore());
        locationTxt.setText(item.getLocation());
        descriptionTxt.setText(item.getDescription());

        if(item.isGuide()){
            guideTxt.setText("Guide");
        }else{
            guideTxt.setText("No-Guide");
        }

        VideoView video = findViewById(R.id.videoView);
        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri uri = Uri.parse(videoPath);
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

        int drawableResId = getResources().getIdentifier(item.getPic(),"drawable",getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        backBtn.setOnClickListener(view -> finish());
    }

    private void initView(){
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        guideTxt = findViewById(R.id.guideTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);
        addFavoris = findViewById(R.id.addfavoris);

    }
    private void ajoutFavoris(String touristSpotId){
        SessionUser sessionUser = SessionUser.getSessionUser();
        String url = "https://back-tourisme-git-main-matthieurt.vercel.app/user/addfavoris";
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(detailPlaceActivity.this);

        // Create JSON object to add favoris
        JSONObject jsonAddFavoris = new JSONObject();
        try {
            jsonAddFavoris.put("iduser", sessionUser.getIdUser());
            jsonAddFavoris.put("touristspot", touristSpotId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Process the response from the server
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            System.out.println(jsonResponse);
                            JSONObject userData = jsonResponse.getJSONObject("success");
                            String userId = userData.getString("_id");
                            String username = userData.getString("nom");
                            List<String > list = new ArrayList<>();
                            JSONArray listFavoris = userData.getJSONArray("favoris");
                            for (int i = 0; i < listFavoris.length(); i++) {
                                String favori = listFavoris.getString(i);
                                list.add(favori);
                            }
                            // Save user data to session
                            sessionUser.setIdUser(userId);
                            sessionUser.setUsername(username);
                            sessionUser.setListFavoris(list);
                            SessionUser.saveSession(detailPlaceActivity.this, userId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Check if the error contains a network response
                if (error.networkResponse != null) {
                    int statusCode = error.networkResponse.statusCode;
                    // Callback to indicate ajoutFavoris failure with the error message and status code
                    sessionUser.setErroMessage(new String(error.networkResponse.data));

                }
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() {
                return jsonAddFavoris.toString().getBytes();
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(stringRequest);
    }

}