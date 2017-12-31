package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Hashtable;

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

        Game.initGame();
        Game.unlockedLevels[0] = true;
        Game.unlockedCategories[0] = true;

        setContentView(R.layout.activity_main);

        ImageButton cat1 = (ImageButton) findViewById(R.id.cat1);
        ImageButton cat2 = (ImageButton) findViewById(R.id.cat2);
        ImageButton cat3 = (ImageButton) findViewById(R.id.cat3);
        ImageButton cat4 = (ImageButton) findViewById(R.id.cat4);

        // przypisanie przyciskom odpowiednich nazw kategorii
        final Hashtable<ImageButton, String> categories = new Hashtable<ImageButton, String> ();
            categories.put(cat1, "Parzystość liczb");
            categories.put(cat2, "Podzielność liczb");
            categories.put(cat3, "Liczby pierwsze i złożone");
            categories.put(cat4, "Ułamki właściwe i niewłaściwe");

        // zdarzenie po wybraniu kategorii
        for (final ImageButton k : categories.keySet()) {
            k.setOnClickListener(new View.OnClickListener() { // klasa anonimowa
                @Override                                     // implementacja metody z interfejsu OnClickListener
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), LevelsMenu.class);
                    i.putExtra("categoryName", categories.get(k)); //przesłanie nazwy kategorii do następnej aktywności
                    startActivity(i);
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // ustawianie blokady kategorii
        for(int i = 1; i <= 4; i++) {
            int id;
            id = getResources().getIdentifier("cat"+i, "id", getPackageName());
            ImageButton category = (ImageButton) findViewById(id);
            boolean unlocked = Game.getCategoryStates(i);

            if (unlocked) {
                category.setEnabled(true);
            }
            else {
                category.setEnabled(false);
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}



