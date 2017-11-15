package com.example.monikam.mathemory;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_main);

        ImageButton cat2 = (ImageButton) findViewById(R.id.cat2);
        cat2.setEnabled(false);

        ImageButton cat3 = (ImageButton) findViewById(R.id.cat3);
        cat3.setEnabled(false);

        ImageButton cat4 = (ImageButton) findViewById(R.id.cat4);
        cat4.setEnabled(false);

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}



