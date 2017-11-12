package com.example.monikam.mathemory;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton cat3 = (ImageButton) findViewById(R.id.cat3);
        cat3.setEnabled(false);

        ImageButton cat4 = (ImageButton) findViewById(R.id.cat4);
        cat4.setEnabled(false);

    }
}





