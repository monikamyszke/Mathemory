package com.example.monikam.mathemory;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FourFieldsGame extends AppCompatActivity {

    int  fieldsNumber = 4;
    String instruction; // polecenie
    String[] sGenerated; // tablica wygenerowanych wartości
    List<Button> buttons = new ArrayList<>();
    CategoryClass category;
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_four_fields_game);

        String categoryName = getIntent().getStringExtra("categoryName");
        int whichLevel = getIntent().getIntExtra("whichLevel", 0);

        category = Game.getCategory(categoryName); // pobranie właściwego obiektu na podstawie nazwy kategorii

        instruction = category.getInstruction(whichLevel); // pobranie polecenia na podstawie poziomu
        TextView task = (TextView) findViewById(R.id.task);
        task.setText(instruction);

        sGenerated = category.generateNumbers(fieldsNumber, whichLevel); // wygenerowanie liczb

        for (int i = 1; i < (fieldsNumber + 1); i++) {
            int id = getResources().getIdentifier("f"+i, "id", getPackageName());
            Button b = (Button) findViewById(id);
            buttons.add(b);
            b.setText(sGenerated[i-1]);
            b.setEnabled(false);
        }
    }

    protected void onResume() {
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
        }, 4000);

        // sprawdzanie po kliknięciu pola
        for (final Button b : buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean correct;
                    correct = category.check(buttons.indexOf(b));
                    if (correct) {
                        b.setText(sGenerated[buttons.indexOf(b)]);
                    }
                    else {
                        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vib.vibrate(250);
                    }

                }
            });
        }

    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
