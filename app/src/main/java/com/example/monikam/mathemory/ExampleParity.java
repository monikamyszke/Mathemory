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

public class ExampleParity extends AppCompatActivity {

    String categoryName;
    CategoryClass category;
    Button next;
    Button num1, result, num2, result2;
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

        category = Game.getCategory(categoryName);

        num1 = (Button) findViewById(R.id.number1);
        result = (Button) findViewById(R.id.result);
        num2 = (Button) findViewById(R.id.number3);
        result2 = (Button) findViewById(R.id.result2);

        next = (Button)  findViewById(R.id.next);

        int number = ((Parity) category).generateForExample(true);
        num1.setText(String.valueOf(number));
        result.setText(String.valueOf(number / 2));

        number = ((Parity) category).generateForExample(false);
        num2.setText(String.valueOf(number));
        result2.setText(String.valueOf(number / 2));
    }

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
