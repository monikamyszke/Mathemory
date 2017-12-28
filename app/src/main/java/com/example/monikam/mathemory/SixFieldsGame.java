package com.example.monikam.mathemory;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class SixFieldsGame extends AppCompatActivity {

    int fieldsNumber = 6;
    String instruction;
    String[] sGenerated;
    List<Button> buttons = new ArrayList<>();
    CategoryClass category;
    Vibrator vib;
    MediaPlayer sound;

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

        category = Game.getCategory(categoryName);

        instruction = category.getInstruction(whichLevel);
        TextView task = (TextView) findViewById(R.id.task);
        task.setText(instruction);

        sGenerated = category.generateNumbers(fieldsNumber, whichLevel);

        for (int i = 1; i < (fieldsNumber + 1); i++) {
            int id = getResources().getIdentifier("f"+i, "id", getPackageName());
            Button b = (Button) findViewById(id);
            buttons.add(b);
            if (categoryName.equals("Ułamki właściwe i niewłaściwe")) {
                b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            }
            b.setText(sGenerated[i-1]);
            b.setEnabled(false);
        }
    }

    protected void onResume() {
        super.onResume();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                for (Button b : buttons) {
                    b.setEnabled(true);
                    b.setText(null);
                }
            }
        }, 6000);

        for (final Button b : buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean correct;
                    correct = category.check(buttons.indexOf(b));
                    if (correct) {
                        b.setText(sGenerated[buttons.indexOf(b)]);
                        sound = MediaPlayer.create(getApplicationContext(), R.raw.correct_answer);
                        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                if(mp != null) {
                                    mp.release();
                                    sound = null;
                                }
                            }
                        });
                        sound.start();
                        b.setEnabled(false);
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
