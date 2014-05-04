package com.jeknowledge.hojehapraxe.app;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HojeHaPraxeAPI extends AsyncTask<Void, Void, String> {
    private String API_LINK = "http://praxe.herokuapp.com/result";

    @Override
    protected String doInBackground(Void... params) {
        URL praxeURL;
        URLConnection praxeConnection;
        BufferedReader reader;
        String rawAPI;
        JSONObject jObject;

        try {
            praxeURL = new URL(API_LINK);
            praxeConnection = praxeURL.openConnection();
            reader = new BufferedReader(new InputStreamReader(praxeConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line.concat("\n"));
            }

            reader.close();
            rawAPI = sb.toString();

            jObject = new JSONObject(rawAPI);

            boolean result;

            try {
                result = jObject.getBoolean("hapraxe");
            } catch(Exception ex) {
                System.out.println("An error occurred: " + ex.toString());
                return "Erro: " + ex.toString();
            }

            if (result) {
                return "Há praxe";
            }

            return "Não há praxe";
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.toString());
            return "Erro: " + ex.toString();
        }
    }
}
