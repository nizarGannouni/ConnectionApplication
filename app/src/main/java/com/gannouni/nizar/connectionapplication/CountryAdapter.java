package com.gannouni.nizar.connectionapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by Nizar on 21/02/2018.
 */
public class CountryAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Country> myCountries;
    private Context context;

    public CountryAdapter(ArrayList<Country> countries) {
        myCountries = countries;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.one_country,parent,false);
        CountryHolder countryHolder = new CountryHolder(view);
        return countryHolder;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((CountryHolder)holder).labelCountry.setText((myCountries.get(position)).getLabelCountry());
        ((CountryHolder)holder).capitalCountry.setText((myCountries.get(position)).getCapitalCountry());
        ((CountryHolder)holder).areaCountry.setText(""+(myCountries.get(position)).getAreaCountry());
    }

    @Override
    public int getItemCount() {
        return myCountries.size();
    }

    class CountryHolder  extends RecyclerView.ViewHolder{
      TextView areaCountry;
      TextView labelCountry;
      TextView capitalCountry;

      public CountryHolder(View itemView) {
          super(itemView);
           areaCountry =  itemView.findViewById(R.id.areaCountry);

           labelCountry = itemView.findViewById(R.id.labelCountry);
           capitalCountry = itemView.findViewById(R.id.capitalCountry);

      }
  }
}
