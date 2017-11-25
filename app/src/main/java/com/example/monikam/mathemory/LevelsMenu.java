package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LevelsMenu extends AppCompatActivity {

    public static final int[] levels= {R.id.lev1, R.id.lev2, R.id.lev3, R.id.lev4, R.id.lev5,
                                             R.id.lev6, R.id.lev7, R.id.lev8, R.id.lev9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_levels_menu);

        String s = getIntent().getStringExtra("categoryName");
       // Game game = (Game) getIntent().getSerializableExtra("game");

        TextView catName = (TextView) findViewById(R.id.cat_name);
        catName.setText(s); // wyświetlenie nazwy kategorii na górze ekranu

        final List<Button> levelButtons = new ArrayList<Button>(); // lista z poziomami 1-9

        for(int id : levels) {
            Button b = (Button) findViewById(id);
            levelButtons.add(b);
        }

        for(Button b : levelButtons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                        Intent i = null;
                        // przyporządkowanie poziomom odpowiednich plansz
                        if(levelButtons.indexOf(v)<3)
                             i = new Intent(getApplicationContext(), FourFieldsGame.class);

                        else if (levelButtons.indexOf(v)<6)
                            i = new Intent(getApplicationContext(), SixFieldsGame.class);

                        else if (levelButtons.indexOf(v)<9)
                            i = new Intent(getApplicationContext(), NineFieldsGame.class);

                        startActivity(i);

                }
            });
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
