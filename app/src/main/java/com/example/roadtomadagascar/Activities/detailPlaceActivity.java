package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.roadtomadagascar.Adapters.HotelAdapter;
import com.example.roadtomadagascar.Adapters.PlaceAdapter;
import com.example.roadtomadagascar.Domains.HotelDomain;
import com.example.roadtomadagascar.Domains.PlaceDomain;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import util.SessionUser;

public class detailPlaceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterHotel;
    private TextView titleTxt,locationTxt,guideTxt,descriptionTxt,scoreTxt;
    private PlaceDomain item;
    private ImageView picImg, backBtn;
    private ImageView addFavoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        initView();
        setVariable();
    }

    private void setVariable(){
        item = (PlaceDomain) getIntent().getSerializableExtra("object");

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
        String videoPath = "android.resource://"+getPackageName()+"/raw/"+item.getUrl();
        System.out.println("HERE IS VIDEO   "+videoPath);
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

        ArrayList<HotelDomain> items = new ArrayList<HotelDomain>();

        items.add(new HotelDomain("Royal Resort","Madagascar","Découvrez l'ultime refuge en bord de mer au sein de notre luxueux hôtel. Niché entre le sable doré et les eaux cristallines, cet établissement incarne l'élégance. Offrant des suites somptueuses avec vues panoramiques, un spa apaisant, une cuisine raffinée en front de mer et un service impeccable, une expérience côtière inoubliable vous attend.",2,true,4,"ifatybeachclub",true,5000));
        items.add(new HotelDomain("Coco lodge","Madagascar","Plongez dans un paradis de raffinement à notre hôtel en bord de plage. Avec un design exquis inspiré de l'environnement côtier, des chambres et suites somptueuses offrant des vues spectaculaires, des restaurants primés mettant en avant une cuisine locale raffinée, et des équipements haut de gamme, profitez d'une expérience balnéaire luxueuse et inégalée.",2,true,4,"jardinduroy",true,5000));
        items.add(new HotelDomain("Hôtel du rivage","Madagascar","Bienvenue à notre havre de luxe en front de mer, où l'élégance rencontre la plage. Des suites somptueuses avec balcons privatifs offrant des vues à couper le souffle sur l'océan, une cuisine gastronomique qui éveille les papilles, des piscines scintillantes et un service attentionné créent une retraite côtière exclusive, redéfinissant le confort et l'opulence.",2,true,4,"antsanitiaresort",true,5000));

        recyclerView = findViewById(R.id.view_hotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterHotel = new HotelAdapter(items);
        recyclerView.setAdapter(adapterHotel);
    }

    private void initView(){
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        guideTxt = findViewById(R.id.guideTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);
        //addFavoris = findViewById(R.id.);

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