package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.roadtomadagascar.Adapters.ListAdapter;
import com.example.roadtomadagascar.Adapters.PopularAdapter;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private RecyclerView.Adapter listAdapter,adapterCat;
    private RecyclerView recyclerViewPopular,recyclerViewCategory;

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
                        break;
                    case "button2_action":
                        actionTxt.setText("You clicked the 'Button 2' button.");
                        break;
                    // Add other cases for other buttons...
                    default:
                        actionTxt.setText("Unknown action.");
                        break;
                }
            }
        }

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