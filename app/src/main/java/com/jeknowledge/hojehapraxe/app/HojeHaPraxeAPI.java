package com.jeknowledge.hojehapraxe.app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class PraxeReturnObject extends Object {
    public boolean result;
}

public class HojeHaPraxeAPI extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... params) {
        URL praxeURL;
        URLConnection praxeConnection;
        BufferedReader in;
        String result = "Erro.";

        try {
            praxeURL = new URL("http://praxe.herokuapp.com/result");
            praxeConnection = praxeURL.openConnection();
            in = new BufferedReader(new InputStreamReader(praxeConnection.getInputStream()));

            String inputLine = in.readLine();
            in.close();

            if (inputLine.contains("false")) {
                result = "Não há praxe.";
            } else if (inputLine.contains("true")) {
                result = "Há praxe.";
            }
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.toString());
        }

        return result;
    }
}
