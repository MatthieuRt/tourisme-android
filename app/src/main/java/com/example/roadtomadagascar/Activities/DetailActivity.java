package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTxt,locationTxt,bedTxt,guideTxt,wifiTxt,descriptionTxt,scoreTxt;
    private PopularDomain item;
    private ImageView picImg, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setVariable();
    }

    private void setVariable(){
        item = (PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        scoreTxt.setText(""+(int) item.getScore());
        locationTxt.setText(item.getLocation());
        bedTxt.setText(item.getBed()+"Bed");
        descriptionTxt.setText(item.getDescription());

        if(item.isGuide()){
            guideTxt.setText("Guide");
        }else{
            guideTxt.setText("No-Guide");
        }
        if(item.isWifi()){
            wifiTxt.setText("Wifi");
        }else{
            wifiTxt.setText("No-Wifi");
        }

        int drawableResId = getResources().getIdentifier(item.getPic(),"drawable",getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        backBtn.setOnClickListener(view -> finish());
    }

    private void initView(){
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        bedTxt = findViewById(R.id.bedTxt);
        guideTxt = findViewById(R.id.guideTxt);
        wifiTxt = findViewById(R.id.wifiTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);
    }
}