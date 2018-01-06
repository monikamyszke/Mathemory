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

/**
 * Podzielność przez 3 - przykład
 */
public class ExampleDivisibility1 extends AppCompatActivity implements View.OnTouchListener{

    /**Przycisk z liczbą dziesiątek*/
    private Button num1;
    /**Przycisk z liczbą jedności*/
    private Button num2;
    /**Liczba podzielna przez 3*/
    private int number;
    /**Liczba dziesiątek*/
    private int number1;
    /**Liczba jedności*/
    private int number2;
    /**Przycisk z sumą number1+number2*/
    private Button sum;
    /**Przycisk przejścia dalej*/
    private Button next;
    /**Nazwa kategorii*/
    private String categoryName;
    /**Przemieszczenie wzdłuż osi X*/
    private float dx;
    /**Przemieszczenie wzdłuż osi Y*/
    private float dy;
    /**Współrzędne X punktu początkowego pól do przeciągnięcia*/
    private float startX;
    /**Współrzędne Y punktu początkowego pól do przeciągnięcia*/
    private float startY;
    /**Zmienna reprezentująca zawartość pola 1 do upuszczania elementów*/
    private boolean full1 = false;
    /**Zmienna reprezentująca zawartość pola 2 do upuszczania elementów*/
    private boolean full2 = false;
    /**Pole tekstowe pojawiające się po przeciągnięciu*/
    private TextView text1;
    /**Pole tekstowe pojawiające się po przeciągnięciu*/
    private TextView text2;

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

        setContentView(R.layout.activity_example_divisibility1);

        categoryName = getIntent().getStringExtra("categoryName");
        TextView cat_name = (TextView) findViewById(R.id.t1);
        cat_name.setText(categoryName);

        CategoryClass category = Game.getCategory(categoryName);

        num1 = (Button) findViewById(R.id.number1);
        num2 = (Button) findViewById(R.id.number2);
        sum = (Button) findViewById(R.id.sum);
        next = (Button)  findViewById(R.id.next);

        text1 = (TextView) findViewById(R.id.textView6);
        text1.setVisibility(View.INVISIBLE);
        text2 = (TextView) findViewById(R.id.textView9);
        text2.setVisibility(View.INVISIBLE);

        assert category != null;
        number = ((Divisibility) category).generateForExample(); // generowanie liczby podzielnej przez 3
        number1 = (number / 10);
        number2 = (number % 10);
        num1.setText(String.valueOf(number1));
        num2.setText(String.valueOf(number2));

        TextView value = (TextView) findViewById(R.id.value);
        value.setText(String.valueOf(number));
    }

    /**
     * Funkcja wywoływana przy wznawianiu aktywności
     */
    @Override
    protected void onResume() {
        super.onResume();

        num1.setOnTouchListener(this);
        num2.setOnTouchListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getApplicationContext(), ExampleDivisibility2.class);
                i.putExtra("categoryName", categoryName);
                startActivity(i);
            }
        });

    }

    /**
     * Nadpisanie funkcji odpowiedzialnej za przesuwanie elementu
     * @param v przesuwany element
     * @param event obiekt przechowujący informację o akcji wykonywanej na elemencie v
     * @return true, jeżeli akcja została zakończona
     */
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

                if ((Math.abs(v.getY() - target1.getY()) < 50 && Math.abs(v.getX() - target1.getX()) < 50) && !full1) {
                    v.animate().x(centreX1).y(centreY1).setDuration(0).start(); // umieszczenie w pierwszym kwadracie

                    full1 = true;
                }
                else if ((Math.abs(v.getY() - target2.getY()) < 50 && Math.abs(v.getX() - target2.getX()) < 50)  && !full2) {
                    v.animate().x(centreX2).y(centreY2).setDuration(0).start(); // umieszczenie w drugim kwadracie
                    full2 = true;
                }
                else {
                    v.animate().x(startX).y(startY).setDuration(0).start(); // powrót pól do pozycji startowej
                }
                // zdarzenia po przeciągnięciu obu pól w odpowiednie miejsca
                if (full1 && full2) {
                    sum.setText(String.valueOf(number1 + number2));
                    text1.setVisibility(View.VISIBLE);
                    text2.setVisibility(View.VISIBLE);
                    TextView sumResult = (TextView) findViewById(R.id.sumResult);
                    TextView originalNum = (TextView) findViewById(R.id.originalNum);
                    sumResult.setText(String.valueOf(number1 + number2));
                    originalNum.setText(String.valueOf(number));
                }

                break;

            default:
                return false;
        }
        return true;
    }

    /**
     * Funkcja załączająca niestandardową czcionkę
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
