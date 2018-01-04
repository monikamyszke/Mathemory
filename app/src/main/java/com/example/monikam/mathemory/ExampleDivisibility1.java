package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ExampleDivisibility1 extends AppCompatActivity implements View.OnTouchListener{

    ImageView im1;
    ImageView im2;
    Button next;
    String categoryName;
    float dx;
    float dy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_example_divisibility1);

        categoryName = getIntent().getStringExtra("categoryName");
        TextView category = (TextView) findViewById(R.id.t1);
        category.setText(categoryName);

        next = (Button)  findViewById(R.id.next);

        im1 = (ImageView) findViewById(R.id.number1);
        im2 = (ImageView) findViewById(R.id.number2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        im1.setOnTouchListener(this);
        im2.setOnTouchListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i = new Intent(getApplicationContext(), ExampleDivisibility2.class);
                i.putExtra("categoryName", categoryName);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: // wciśnięcie pola

                dx = v.getX() - event.getRawX();
                dy = v.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE: // przeciąganie pola

                v.animate()
                        .x(event.getRawX() + dx)
                        .y(event.getRawY() + dy)
                        .setDuration(0)
                        .start();
                break;
            case MotionEvent.ACTION_UP: // puszczenie pola

                break;
            default:
                return false;
        }
        return true;
    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
