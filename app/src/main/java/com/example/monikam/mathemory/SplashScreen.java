package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread mySplashScreen = new Thread(){
            public void run(){
                try {
                        ImageView logo = (ImageView) findViewById(R.id.imageView);
                        Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                        Animation fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

                        AnimationSet s = new AnimationSet(false);

                        s.addAnimation(fadeInAnimation);
                        s.addAnimation(fadeOutAnimation);
                        s.setDuration(3000);

                        logo.startAnimation(s);
                        logo.setVisibility(View.INVISIBLE);

                       while (s.hasEnded() == false) {
                          sleep(10);
                   }

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        mySplashScreen.start();
    }
}


