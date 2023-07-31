package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.roadtomadagascar.Adapters.ListAdapter;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

import java.util.ArrayList;

public class FavorisActivity extends AppCompatActivity {

    private RecyclerView.Adapter listAdapter,adapterCat;
    private RecyclerView recyclerViewPopular,recyclerViewCategory;

    private ImageView backBtn;

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
        items.add(new PopularDomain("Plage d'Antsanitia","Majunga","Ceci est une description",2,true,4.8,"pic1",true,1000));
        items.add(new PopularDomain("Allée des Baobabs","Morondava","Ceci est une description",1,false,5,"pic2",false,2500));
        items.add(new PopularDomain("Foulpointe","Foulpointe","Ceci est une description",3,true,4.8,"pic1",true,1000));

        recyclerViewPopular= findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listAdapter=new ListAdapter(items);
        recyclerViewPopular.setAdapter(listAdapter);
    }
}