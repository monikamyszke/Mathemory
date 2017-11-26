package com.example.monikam.mathemory;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SixFieldsGame extends AppCompatActivity {

    int fieldsNumber = 6;
    String[] sGenerated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_six_fields_game);

        String categoryName = getIntent().getStringExtra("categoryName");
        int whichLevel = getIntent().getIntExtra("whichLevel", 0);

        CategoryClass category = Game.getCategory(categoryName);

        sGenerated = category.generateNumbers(fieldsNumber, whichLevel);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
