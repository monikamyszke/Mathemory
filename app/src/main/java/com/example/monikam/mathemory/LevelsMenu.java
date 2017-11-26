package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LevelsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_levels_menu);

        final String categoryName = getIntent().getStringExtra("categoryName");
        TextView cat_name = (TextView) findViewById(R.id.cat_name);
        cat_name.setText(categoryName); // wyświetlenie nazwy kategorii na górze ekranu

        final List<Button> levelButtons = new ArrayList<Button>(); // lista z poziomami 1-9

        for(int i = 1; i < 10; i++) {
            int id = getResources().getIdentifier("lev"+i, "id", getPackageName());
            Button b = (Button) findViewById(id);
            levelButtons.add(b);
        }

        for(Button b : levelButtons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                        Intent i = null;

                        // przyporządkowanie poziomom odpowiednich plansz
                        if (levelButtons.indexOf(v) > 0 && levelButtons.indexOf(v) < 3) {
                            i = new Intent(getApplicationContext(), FourFieldsGame.class);
                        }
                        else if (levelButtons.indexOf(v) > 2 && levelButtons.indexOf(v) < 6) {
                            i = new Intent(getApplicationContext(), SixFieldsGame.class);
                        }
                        else {
                            i = new Intent(getApplicationContext(), NineFieldsGame.class);
                        }

                        i.putExtra("categoryName", categoryName); // przesłanie informacji o wybranej kategorii
                        i.putExtra("whichLevel", levelButtons.indexOf(v)+1); // przesłanie informacji który to poziom
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
