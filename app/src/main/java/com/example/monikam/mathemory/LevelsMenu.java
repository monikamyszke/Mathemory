package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LevelsMenu extends AppCompatActivity {

    CategoryClass category;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_levels_menu);

        categoryName = getIntent().getStringExtra("categoryName");
        TextView cat_name = (TextView) findViewById(R.id.cat_name);
        cat_name.setText(categoryName); // wyświetlenie nazwy kategorii na górze ekranu

        category = Game.getCategory(categoryName);

        final List<Button> levelButtons = new ArrayList<Button>(); // lista z przyciskami przykładu i poziomów 1-10

        Button e = (Button) findViewById(R.id.example);
        levelButtons.add(e);
        e.setEnabled(true);

        for (int i = 1; i < 11; i++) {
            int id = getResources().getIdentifier("lev"+i, "id", getPackageName());
            Button b = (Button) findViewById(id);
            levelButtons.add(b);

        }

        for (Button b : levelButtons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                        Intent i = null;

                        // przyporządkowanie poziomom odpowiednich plansz
                        if (levelButtons.indexOf(v) == 0) {
                            i = new Intent(getApplicationContext(), ExampleDivisibility1.class);
                        }
                        else if (levelButtons.indexOf(v) > 1 && levelButtons.indexOf(v) < 5) {
                            i = new Intent(getApplicationContext(), FourFieldsGame.class);
                        }
                        else if (levelButtons.indexOf(v) > 4 && levelButtons.indexOf(v) < 8) {
                            i = new Intent(getApplicationContext(), SixFieldsGame.class);
                        }
                        else {
                            i = new Intent(getApplicationContext(), NineFieldsGame.class);
                        }

                    i.putExtra("categoryName", categoryName); // przesłanie informacji o wybranej kategorii
                    if (levelButtons.indexOf(v) != 0) {
                        i.putExtra("whichLevel", levelButtons.indexOf(v)); // przesłanie informacji który to poziom
                    }
                        startActivity(i);

                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // ustawianie blokady poziomów oraz liczby gwiazdek
        for (int i = 1; i < 11; i++) {
            int id;
            id = getResources().getIdentifier("lev"+i, "id", getPackageName());
            Button b = (Button) findViewById(id);
            boolean unlocked = Game.getLevelStates(category, i);

            if (unlocked) {
                b.setEnabled(true);
            }
            else {
                b.setEnabled(false);
            }

            int star = Game.getStars(category, i);
            id = getResources().getIdentifier("s"+i, "id", getPackageName());
            ImageView stars = (ImageView) findViewById(id);

            if (star == 1){
                stars.setImageResource(R.drawable.one_yellow);
            }
            else if (star == 2) {
                stars.setImageResource(R.drawable.two_yellow);
            }
            else if (star == 3) {
                stars.setImageResource(R.drawable.three_yellow);
            }
            else {
                stars.setImageResource(R.drawable.all_grey);
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
