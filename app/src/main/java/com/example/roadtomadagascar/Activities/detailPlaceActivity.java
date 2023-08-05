package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.roadtomadagascar.Domains.PlaceDomain;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

public class detailPlaceActivity extends AppCompatActivity {

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
    }
}