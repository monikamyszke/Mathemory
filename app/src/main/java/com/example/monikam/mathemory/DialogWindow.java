package com.example.monikam.mathemory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

class DialogWindow extends Dialog implements android.view.View.OnClickListener {

    private Button repeatLevel;
    private Button menu;
    private Context context;
    private DialogWindow dialogContext = this;

    private String categoryName;
    private int whichLevel;
    //okienko po nieudanym przejściu poziomu
    DialogWindow(Context context, int whichLevel, String categoryName) {
        super(context);
        this.context = context;
        this.whichLevel = whichLevel;
        this.categoryName = categoryName;
        setContentView(R.layout.uncompleted_level);
        repeatLevel = (Button) findViewById(R.id.repeat);
        menu = (Button) findViewById(R.id.menu);
        repeatLevel.setOnClickListener(this);
        menu.setOnClickListener(this);

        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }
    //okienko po udanym przejściu poziomu
    DialogWindow(Context context, int whichLevel, String categoryName, int stars) {
        super(context);
        this.context = context;
        this.whichLevel = whichLevel;
        this.categoryName = categoryName;
        setContentView(R.layout.completed_level);
        Button nextLevel = (Button) findViewById(R.id.next);
        repeatLevel = (Button) findViewById(R.id.repeat);
        menu = (Button) findViewById(R.id.menu);
        nextLevel.setOnClickListener(this);
        repeatLevel.setOnClickListener(this);
        menu.setOnClickListener(this);

        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);

        showStars(stars);
    }

    // funkcja wyświetlająca zdobyte gwiazdki w okienku
    private void showStars(int stars) {
        ImageView starsResult = (ImageView) findViewById(R.id.stars);
        switch (stars) {
            case 1: starsResult.setImageResource(R.drawable.one_yellow);
                break;
            case 2: starsResult.setImageResource(R.drawable.two_yellow);
                break;
            case 3: starsResult.setImageResource(R.drawable.three_yellow);
                break;
            default: starsResult.setImageResource(R.drawable.all_grey);
                break;
        }
    }

    // opis zdarzeń po kliknięciu w 1 z 3 możliwych przycisków dostępnych w okienkach
    @Override
    public void onClick(View v) {

        Intent i = null;
        switch (v.getId()) {

            case R.id.next:

                if (context.getClass().equals(FourFieldsGame.class)) {
                    if (whichLevel == 4) {
                        i = new Intent(context, SixFieldsGame.class);
                    }
                    else {
                        i = new Intent(context, FourFieldsGame.class);
                    }
                }
                else if (context.getClass().equals(SixFieldsGame.class)) {
                    if (whichLevel == 7) {
                        i = new Intent(context, NineFieldsGame.class);
                    }
                    else {
                        i = new Intent(context, SixFieldsGame.class);
                    }
                }
                else {
                    if (whichLevel == 1) {
                        i = new Intent(context, FourFieldsGame.class);
                    }
                    else if (whichLevel == 10) {
                        i = new Intent(context, MainActivity.class);
                    }
                    else {
                        i = new Intent(context, NineFieldsGame.class);
                    }
                }
                i.putExtra("whichLevel", whichLevel + 1);
                break;

            case R.id.repeat:

                i = new Intent(context, context.getClass());
                i.putExtra("whichLevel", whichLevel);
                break;

            case R.id.menu:
                i = new Intent(context, LevelsMenu.class);
                break;

        }

            assert i != null;
            i.putExtra("categoryName", categoryName);
            context.startActivity(i);
            ((Activity) context).finish();
            dialogContext.dismiss();

    }

}
