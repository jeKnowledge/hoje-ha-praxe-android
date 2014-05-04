package com.jeknowledge.hojehapraxe.app;

import android.os.AsyncTask;

import org.json.JSONObject;

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
        BufferedReader reader;
        String rawAPI = "Erro.";
        JSONObject jObject = null;

        try {
            praxeURL = new URL("http://praxe.herokuapp.com/result");
            praxeConnection = praxeURL.openConnection();
            reader = new BufferedReader(new InputStreamReader(praxeConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            reader.close();
            rawAPI = sb.toString();

            jObject = new JSONObject(rawAPI);
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.toString());
        }

        boolean result = false;

        try {
            result = jObject.getBoolean("hapraxe");
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.toString());
            return "Erro: " + ex.toString();
        }

        if (result) {
            return "Há praxe";
        }

        return "Não há praxe";
    }
}
