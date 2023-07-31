package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roadtomadagascar.Adapters.CategoryAdapter;
import com.example.roadtomadagascar.Adapters.PopularAdapter;
import com.example.roadtomadagascar.Domains.CategoryDomain;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular, adapterCat;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;

    private TextView listLieux, listCat, listeRecherche;

    ImageView notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Plage d'Antsanitia", "Majunga", "Ceci est une description", 2, true, 4.8, "pic1", true, 1000));
        items.add(new PopularDomain("Allée des Baobabs", "Morondava", "Ceci est une description", 1, false, 5, "pic2", false, 2500));
        items.add(new PopularDomain("Foulpointe", "Foulpointe", "Ceci est une description", 3, true, 4.8, "pic1", true, 1000));

        recyclerViewPopular = findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);

        ArrayList<CategoryDomain> catList = new ArrayList<>();

        catList.add(new CategoryDomain("Plages", "cat1"));
        catList.add(new CategoryDomain("Camps", "cat2"));
        catList.add(new CategoryDomain("Forest", "cat3"));
        catList.add(new CategoryDomain("Desert", "cat4"));
        catList.add(new CategoryDomain("Mountain", "cat5"));

        recyclerViewCategory = findViewById(R.id.view_cat);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterCat = new CategoryAdapter(catList);
        recyclerViewCategory.setAdapter(adapterCat);

        listLieux = findViewById(R.id.listLieux);
        listLieux.setOnClickListener(view -> {
            Intent intent = new Intent(Menu.this, ListActivity.class);
            intent.putExtra("action", "Lieux");
            startActivity(intent);
        });
    }
}