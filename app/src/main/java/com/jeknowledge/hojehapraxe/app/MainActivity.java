package com.jeknowledge.hojehapraxe.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    PraxeAPIObject praxeObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            praxeObject = new HojeHaPraxeAPI().execute().get();
            updateInterface();
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.toString());
        }

        Button praxeButton = (Button) findViewById(R.id.ha_praxe);

        praxeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    praxeObject = new HojeHaPraxeAPI().execute().get();
                    updateInterface();
                } catch (Exception ex) {
                    System.out.println("An error occurred: " + ex.toString());
                }
            }
        });
    }

    private void updateInterface() {
        TextView bigAnswer = (TextView) findViewById(R.id.big_answer_textview);
        TextView reason = (TextView) findViewById(R.id.reason_textview);
        TextView notification = (TextView) findViewById(R.id.notification_textview);

        bigAnswer.setText(praxeObject.bigAnswer);
        reason.setText(praxeObject.reason);
        notification.setText(praxeObject.notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
