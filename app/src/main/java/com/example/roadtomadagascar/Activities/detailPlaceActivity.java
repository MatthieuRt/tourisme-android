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

import com.bumptech.glide.Glide;
import com.example.roadtomadagascar.Adapters.HotelAdapter;
import com.example.roadtomadagascar.Adapters.PlaceAdapter;
import com.example.roadtomadagascar.Domains.HotelDomain;
import com.example.roadtomadagascar.Domains.PlaceDomain;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

import java.util.ArrayList;

public class detailPlaceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterHotel;
    private TextView titleTxt,locationTxt,guideTxt,descriptionTxt,scoreTxt;
    private PlaceDomain item;
    private ImageView picImg, backBtn;

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

        items.add(new HotelDomain("Andilana Beach","NosyBe","C'est un hôtel",2,true,4,"pic1",true,5000));
        items.add(new HotelDomain("Andilana Beach","NosyBe","C'est un hôtel",2,true,4,"pic1",true,5000));
        items.add(new HotelDomain("Andilana Beach","NosyBe","C'est un hôtel",2,true,4,"pic1",true,5000));

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
    }
}