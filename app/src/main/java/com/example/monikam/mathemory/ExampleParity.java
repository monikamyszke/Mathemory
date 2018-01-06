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
 * Parzystość liczb - przykład
 */
public class ExampleParity extends AppCompatActivity {

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

        setContentView(R.layout.activity_example_parity);

        categoryName = getIntent().getStringExtra("categoryName");
        TextView cat_name = (TextView) findViewById(R.id.t1);
        cat_name.setText(categoryName);

        CategoryClass category = Game.getCategory(categoryName);


        Button num1 = (Button) findViewById(R.id.number1);
        Button result = (Button) findViewById(R.id.result);
        Button num2 = (Button) findViewById(R.id.number3);
        Button result2 = (Button) findViewById(R.id.result2);

        next = (Button)  findViewById(R.id.next);

        assert category != null;
        int number = ((Parity) category).generateForExample(true);
        num1.setText(String.valueOf(number));
        result.setText(String.valueOf(number / 2));

        number = ((Parity) category).generateForExample(false);
        num2.setText(String.valueOf(number));
        result2.setText(String.valueOf(number / 2));
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
