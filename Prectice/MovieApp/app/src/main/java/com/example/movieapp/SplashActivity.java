package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ImageView imageview2,imageView3;
    Animation popcornScalling, mediatranslate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        imageview2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        popcornScalling = AnimationUtils.loadAnimation(this,R.anim.scalling);
        mediatranslate = AnimationUtils.loadAnimation(this,R.anim.translate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                imageview2.startAnimation(popcornScalling);
                imageView3.startAnimation(mediatranslate);
                startActivity(intent);
            }
        },4000);


    }
}