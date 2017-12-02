package com.example.monikam.mathemory;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SixFieldsGame extends AppCompatActivity {

    int fieldsNumber = 6;
    String instruction;
    String[] sGenerated;
    List<Button> buttons = new ArrayList<>();

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

        instruction = category.getInstruction(whichLevel);
        TextView task = (TextView) findViewById(R.id.task);
        task.setText(instruction);

        sGenerated = category.generateNumbers(fieldsNumber, whichLevel);

        for(int i = 1; i < (fieldsNumber + 1); i++) {
            int id = getResources().getIdentifier("f"+i, "id", getPackageName());
            Button b = (Button) findViewById(id);
            buttons.add(b);
            b.setText(sGenerated[i-1]);
            b.setEnabled(false);
        }
    }

    protected void onResume(){
        super.onResume();

        // ukrycie liczb z pól planszy po upłynięciu czasu
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                for (Button b : buttons) {
                    b.setEnabled(true);
                    b.setText(null);
                }
            }
        }, 6000);

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
