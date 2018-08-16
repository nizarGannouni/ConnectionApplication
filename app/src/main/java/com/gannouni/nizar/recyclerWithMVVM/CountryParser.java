package com.gannouni.nizar.recyclerWithMVVM;

import android.content.Context;
import android.net.Uri;

import com.gannouni.nizar.recyclerWithMVVM.Model.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nizar on 21/02/2018.
 */
public class CountryParser {
    private ArrayList<Country> myCountries;
    private int numContinent;
    private Context context;

    public CountryParser(int numContinent) {
        myCountries = new ArrayList<>();
        this.numContinent= numContinent;
    }

    public void parser(){
        try{
            StringBuilder sb = new StringBuilder("");
            BufferedReader br = null;
            URL url= new URL("http://www.ticanalis.info/allCountries.php");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("num", "" + numContinent);
            builder.appendQueryParameter("etat", "" + 1);
            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
            InputStream is = conn.getInputStream();// is is inputstream

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);

            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");


            }
            is.close();
            String resultat = sb.toString();
            JSONObject ob = new JSONObject(resultat);
            JSONArray result = ob.getJSONArray("cnt");
            for (int i = 0; i < result.length(); i++)
             {
                JSONObject res = result.getJSONObject(i);

                 Country country = new Country(res.getInt("code"), res.getString("label"), res.getString("capital"), (float) res.getDouble("area"), res.getLong("pop"));

                myCountries.add(country);

            }
            conn.disconnect();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Country> getCountries() {
        return myCountries;
    }

    public void setCountries(ArrayList<Country> countries) {
        myCountries = countries;
    }
}
