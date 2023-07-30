package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roadtomadagascar.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout introBtn=findViewById(R.id.introBtn);
        introBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,Menu.class)));
    }
}