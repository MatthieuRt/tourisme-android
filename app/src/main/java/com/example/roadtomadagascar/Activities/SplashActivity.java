package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.roadtomadagascar.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView textView3 = findViewById(R.id.textView3);
        animateTextView(textView3);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
    private void animateTextView(TextView textView) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(textView, "scaleX", 0f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(textView, "scaleY", 0f, 1f);
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.setDuration(1000); // Set the animation duration in milliseconds
        animatorSet.start();
    }
}