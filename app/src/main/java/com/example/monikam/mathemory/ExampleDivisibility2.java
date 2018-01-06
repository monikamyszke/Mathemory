package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Podzielność przez 5 - przykład
 */
public class ExampleDivisibility2 extends AppCompatActivity {

    /**Nazwa kategorii*/
    private String categoryName;
    /**Przycisk przejścia do gry*/
    private Button next;

    /**
     * Funkcja wywoływana, gdy aktywność jest tworzona
     * @param savedInstanceState obiekt przechowujący poprzedni stan aktywności
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_example_divisibility2);

        categoryName = getIntent().getStringExtra("categoryName");
        TextView category = (TextView) findViewById(R.id.t1);
        category.setText(categoryName);

        next = (Button)  findViewById(R.id.next);
    }

    /**
     * Funkcja wywoływana przy wznawianiu aktywności
     */
    @Override
    protected void onResume() {
        super.onResume();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getApplicationContext(), NineFieldsGame.class);
                i.putExtra("categoryName", categoryName);
                i.putExtra("whichLevel", 1);
                startActivity(i);
            }
        });

    }

    /**
     * Funkcja załączająca niestandardową czcionkę
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
