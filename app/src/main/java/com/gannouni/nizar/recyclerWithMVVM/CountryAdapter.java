package com.gannouni.nizar.recyclerWithMVVM;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gannouni.nizar.recyclerWithMVVM.Model.Country;
import com.gannouni.nizar.recyclerWithMVVM.databinding.OneCountryBinding;

import java.util.ArrayList;

/**
 * Created by Nizar on 21/02/2018.
 */
public class CountryAdapter  extends  RecyclerView.Adapter<CountryAdapter.CountryHolder>{
    private ArrayList<Country> myCountries;
    //private Context context;

    public CountryAdapter(ArrayList<Country> countries) {
        myCountries = countries;
    }


    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // context = parent.getContext();
         LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OneCountryBinding binding = DataBindingUtil.inflate(inflater, R.layout.one_country, parent, false);
        return new CountryHolder(binding);

    }

    @Override
    public void onBindViewHolder(final CountryHolder holder, int position) {

        final Country country = myCountries.get(position);
        holder.bind(country);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(), country.getCapitalCountry(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public int getItemCount() {
        return myCountries.size();
    }

    public class CountryHolder  extends RecyclerView.ViewHolder{
        private OneCountryBinding myOneCountryBinding;

      public CountryHolder(OneCountryBinding myOneCountryBinding) {
          super(myOneCountryBinding.getRoot());
           this.myOneCountryBinding= myOneCountryBinding;

      }
        public void bind( Country item) {
            myOneCountryBinding.setCountryVm( item);
            myOneCountryBinding.executePendingBindings();
        }


    }
}
