package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.roadtomadagascar.R;

public class SettingsActivity extends AppCompatActivity {

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int currentNightMode = AppCompatDelegate.getDefaultNightMode();
                int newNightMode = b ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
                if (currentNightMode != newNightMode) {
                    AppCompatDelegate.setDefaultNightMode(newNightMode);
                    getDelegate().applyDayNight();
                    recreate();
                }
            }
        });

    }
}