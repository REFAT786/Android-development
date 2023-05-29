package com.ibss.vpn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibss.vpn.R;
import com.ibss.vpn.dao.CountryDao;
import com.ibss.vpn.domain.Country;

import java.util.ArrayList;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {

    ArrayList<Country> countries;
    Context context;
    private final CountryDao listener;

    public CountryListAdapter(ArrayList<Country> countries, Context context, CountryDao listener) {
        this.countries = countries;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Country currentUser = countries.get(position);
        holder.countyText.setText(currentUser.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(currentUser));

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView countyText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.countyText = itemView.findViewById(R.id.countryText_id);

        }
    }
}
