package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FourFieldsGame extends AppCompatActivity {

    int  fieldsNumber = 4;
    int counter;
    int counterIncorrect;
    String instruction; // polecenie
    String[] sGenerated; // tablica wygenerowanych wartości
    List<Button> buttons = new ArrayList<>();
    CategoryClass category;
    Vibrator vib;
    MediaPlayer sound;
    String categoryName;
    int whichLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_four_fields_game);

        categoryName = getIntent().getStringExtra("categoryName");
        whichLevel = getIntent().getIntExtra("whichLevel", 0);

        category = Game.getCategory(categoryName); // pobranie właściwego obiektu na podstawie nazwy kategorii

        instruction = category.getInstruction(whichLevel); // pobranie polecenia na podstawie poziomu
        TextView task = (TextView) findViewById(R.id.task);
        task.setText(instruction);

        sGenerated = category.generateNumbers(fieldsNumber, whichLevel); // wygenerowanie liczb
        counter = category.getCounter();
        counterIncorrect = 0;

        // ustawienie pól z liczbami
        for (int i = 1; i < (fieldsNumber + 1); i++) {
            int id = getResources().getIdentifier("f"+i, "id", getPackageName());
            Button b = (Button) findViewById(id);
            buttons.add(b);
            if (categoryName.equals("Ułamki właściwe i niewłaściwe")) {
                b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
            }
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
                    counter --;
                    b.setText(sGenerated[buttons.indexOf(b)]);
                    sound = MediaPlayer.create(getApplicationContext(), R.raw.correct_answer);
                    sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if (mp != null) {
                                mp.release();
                                sound = null;
                            }
                        }
                    });
                    sound.start();
                    b.setBackgroundResource(R.drawable.check_mark);
                    b.setEnabled(false);


                }
                else {
                    counterIncorrect ++;
                    vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vib.vibrate(250);
                }

                if (counterIncorrect == 3) {
                    Intent i = new Intent(getApplicationContext(), FourFieldsGame.class);
                    i.putExtra("categoryName", categoryName);
                    i.putExtra("whichLevel", whichLevel);
                    startActivity(i);
                    finish();
                }

                if (counter == 0) {

                    Intent i;
                    if (whichLevel == 4) {
                        i = new Intent(getApplicationContext(), SixFieldsGame.class);
                    }
                    else {
                        i = new Intent(getApplicationContext(), FourFieldsGame.class);
                    }

                    i.putExtra("categoryName", categoryName); // przesłanie informacji o wybranej kategorii
                    i.putExtra("whichLevel", whichLevel+1); // przesłanie informacji który to poziom
                    startActivity(i);
                    finish();

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
