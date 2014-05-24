package com.jeknowledge.hojehapraxe.app;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
    private PraxeAPIObject praxeObject;
    private String REFRESH_NAME = "Atualizar";

    private Typeface montagaFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        montagaFont = Typeface.createFromAsset(getAssets(), "montaga_regular.ttf");

        TextView bigAnswer = (TextView) findViewById(R.id.big_answer_textview);
        TextView reason = (TextView) findViewById(R.id.reason_textview);
        TextView notification = (TextView) findViewById(R.id.notification_textview);

        bigAnswer.setTypeface(montagaFont);
        bigAnswer.setTextSize(55);

        reason.setTypeface(montagaFont);

        notification.setTypeface(montagaFont);

        try {
            praxeObject = new HojeHaPraxeAPI().execute().get();
            updateInterface();
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.toString());
        }
    }

    private void updateInterface() {
        TextView bigAnswer = (TextView) findViewById(R.id.big_answer_textview);
        TextView reason = (TextView) findViewById(R.id.reason_textview);
        TextView notification = (TextView) findViewById(R.id.notification_textview);

        if (bigAnswer.getText().equals("Pode haver praxe")) {
            reason.setText("");
            setTheme(android.R.style.Theme_Light_NoTitleBar);
        } else {
            reason.setText(praxeObject.reason);
            setTheme(android.R.style.Theme_Black_NoTitleBar);
        }

        bigAnswer.setText(praxeObject.bigAnswer);
        notification.setText(praxeObject.notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.NONE, Menu.NONE, REFRESH_NAME);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() == REFRESH_NAME) {
            try {
                praxeObject = new HojeHaPraxeAPI().execute().get();
                updateInterface();
            } catch (Exception ex) {
                System.out.println("An error occurred: " + ex.toString());
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
