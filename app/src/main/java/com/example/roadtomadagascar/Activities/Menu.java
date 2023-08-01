package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roadtomadagascar.Adapters.CategoryAdapter;
import com.example.roadtomadagascar.Adapters.PopularAdapter;
import com.example.roadtomadagascar.Domains.CategoryDomain;
import com.example.roadtomadagascar.Domains.PopularDomain;
import com.example.roadtomadagascar.Fragments.Favoris;
import com.example.roadtomadagascar.Fragments.Parametres;
import com.example.roadtomadagascar.R;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular, adapterCat;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;

    private TextView listLieux, listCat, listeRecherche;

    ImageView notif, favorisBtn, parametresBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        notif = findViewById(R.id.notification);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(Menu.this,
                    Manifest.permission.POST_NOTIFICATIONS)!=
            PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Menu.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeNotification();
            }
        });
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

        favorisBtn = findViewById(R.id.favorisBtn);
        parametresBtn = findViewById(R.id.parametresBtn);

        favorisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, FavorisActivity.class);
                startActivity(intent);
            }
        });

        parametresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(Menu.this, SettingsActivity.class);
                startActivity(intent);*/
                replaceFragment(new Favoris());
                System.out.println("--------------------------- CLICKED");
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.myLayout,fragment);
        fragmentTransaction.commit();
    }

    public void makeNotification(){
        String chanelID= "CHANEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), chanelID);
        builder.setSmallIcon(R.drawable.bell)
                .setContentTitle("Notification Title")
                .setContentText("Some text for notification here")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data","Some values to be passed here");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0,intent,PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel(chanelID);
            if(notificationChannel == null ){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(chanelID,
                        "some description",importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(0,builder.build());
    }
}