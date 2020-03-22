package com.ryandro.covid_19_tracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryandro.covid_19_tracker.R;
import com.ryandro.covid_19_tracker.model.Feature;

import java.util.List;

/**
 * To hold the adapter logic for display country wise value
 */

public class CountryAdapter extends  RecyclerView.Adapter<CountryAdapter.UserHolder> {
    private Context context;
    private List<Feature> features;

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView tvCountry;
        TextView tvPasscode;


        public UserHolder(View view) {
            super(view);
            tvCountry = view.findViewById(R.id.tvCountry);
        }
    }

    public void refresh(List<Feature> features) {
        this.features = features;
        notifyDataSetChanged();
    }


    public CountryAdapter(Context context, List<Feature> features) {
        this.context = context;
        this.features = features;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_item, parent, false);

        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.tvCountry.setText(features.get(position).getAttributes().getCountryRegion());
//        holder.tvPasscode.setText(mainResponseObjs.get(position).getPasscode() + "");
    }

    @Override
    public int getItemCount() {
        return features.size();
    }
}
