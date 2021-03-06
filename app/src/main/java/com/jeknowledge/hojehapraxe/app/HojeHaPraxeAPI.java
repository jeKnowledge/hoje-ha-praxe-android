package com.jeknowledge.hojehapraxe.app;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class PraxeAPIObject {
    public String bigAnswer;
    public String reason;
    public String notification;
}

public class HojeHaPraxeAPI extends AsyncTask<Void, Void, PraxeAPIObject> {
    @Override
    protected PraxeAPIObject doInBackground(Void... params) {
        URL praxeURL;
        URLConnection praxeConnection;
        BufferedReader reader;
        String rawAPI;
        JSONObject jObject;
        PraxeAPIObject returnObject = new PraxeAPIObject();
        String API_LINK = "http://praxe.herokuapp.com/result";

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

            String result;

            try {
                result = jObject.getString("hapraxe");
            } catch (Exception ex) {
                System.out.println("An error occurred: " + ex.toString());
                return null;
            }

            System.out.println();
            System.out.println(result);

            if (result.equals("true")) {
                returnObject.bigAnswer = "Hoje pode haver praxe em Coimbra.";
            } else {
                returnObject.bigAnswer = "Hoje não pode haver praxe em Coimbra.";
            }

            returnObject.reason = jObject.getString("reason");
            returnObject.notification = jObject.getString("notification");

            return returnObject;
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.toString());

            returnObject.bigAnswer = "Erro na ligação ao servidor.";

            return returnObject;
        }
    }
}
