package com.gannouni.nizar.connectionapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner mySpinner;
    private RecyclerView myRecyclerView;
    private ArrayList<Country> myCountries;
    private int numContinent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = findViewById(R.id.recyclerCountries);
        mySpinner = findViewById(R.id.spinnerContinents);

        if(savedInstanceState!=null){
            myCountries = (ArrayList<Country>) savedInstanceState.getSerializable("myCountries");
        }
        else {
            myCountries = new ArrayList<>();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item , getResources().getStringArray(R.array.continents));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(arrayAdapter);
        numContinent = 0;
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numContinent = i;

                if(numContinent>0){
                    MyTask2 myTask2 = new MyTask2();
                    myTask2.execute();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myCountries = (ArrayList<Country>) savedInstanceState.getSerializable("myCountries");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("myCountries",myCountries);
    }




    private void displayData(){
        if(myCountries.size()>0){
            CountryAdapter countryAdapter = new CountryAdapter(myCountries);
            RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext());
            myRecyclerView.setLayoutManager(layout);
            myRecyclerView.setAdapter(countryAdapter);

        }


    }


    private class MyTask2 extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            displayData();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            CountryParser countryParser = new CountryParser(numContinent);
            countryParser.parser();
            myCountries=countryParser.getCountries();


            return null;
        }
    }
}
