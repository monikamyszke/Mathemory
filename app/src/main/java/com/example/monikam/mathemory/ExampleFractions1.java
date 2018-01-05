package com.example.monikam.mathemory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ExampleFractions1 extends AppCompatActivity implements View.OnTouchListener {

    CategoryClass category;
    String categoryName;
    Button num1;
    Button num2;
    Button next;
    int number1;
    int number2;
    int[] fraction;
    float dx;
    float dy;
    float startX;
    float startY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/righteous.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_example_fractions1);

        categoryName = getIntent().getStringExtra("categoryName");
        TextView cat_name = (TextView) findViewById(R.id.t1);
        cat_name.setText(categoryName);

        category = Game.getCategory(categoryName);

        num1 = (Button) findViewById(R.id.number1);
        num2 = (Button) findViewById(R.id.number2);
        next = (Button)  findViewById(R.id.next);

        fraction = ((Fractions) category).generateForExample(); // generowanie liczby podzielnej przez 3
        number1 = fraction[0];
        number2 = fraction[1];
        num1.setText(String.valueOf(number1));
        num2.setText(String.valueOf(number2));
    }

    @Override
    protected void onResume() {
        super.onResume();

        num1.setOnTouchListener(this);
        num2.setOnTouchListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getApplicationContext(), ExampleFractions2.class);
                i.putExtra("categoryName", categoryName);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: // wciśnięcie pola

                startX = v.getX();
                startY = v.getY();
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

                ImageView target1 = (ImageView) findViewById(R.id.box1);
                ImageView target2 = (ImageView) findViewById(R.id.box2);
                float centreX1 = target1.getX() + 0.5f * target1.getWidth() - 0.5f * v.getWidth();
                float centreY1 = target1.getY() + 0.5f * target1.getHeight() - 0.5f * v.getHeight();

                float centreX2 = target2.getX() + 0.5f * target2.getWidth() - 0.5f * v.getWidth();
                float centreY2 = target2.getY() + 0.5f * target2.getHeight() - 0.5f * v.getHeight();

                if ((Math.abs(v.getY() - target1.getY()) < 50 && Math.abs(v.getX() - target1.getX()) < 50) && (v.getId() == R.id.number1)) {

                    v.animate().x(centreX1).y(centreY1).setDuration(0).start(); // umieszczenie w pierwszym kwadracie

                }
                else if ((Math.abs(v.getY() - target2.getY()) < 50 && Math.abs(v.getX() - target2.getX()) < 50) && (v.getId() == R.id.number2)) {

                    v.animate().x(centreX2).y(centreY2).setDuration(0).start(); // umieszczenie w drugim kwadracie

                }
                else {
                    v.animate().x(startX).y(startY).setDuration(0).start(); // powrót pól do pozycji startowej
                }

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
