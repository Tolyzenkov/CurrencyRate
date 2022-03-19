package com.example.currencyrate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ValuteAdapter extends RecyclerView.Adapter<ValuteAdapter.RecyclerViewViewHolder> {

    private ArrayList<Valute> arrayList;

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        public ImageView countryFlag;
        public ImageView arrow;
        public TextView countryId;
        public TextView rate;
        public TextView difference;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.countryFlag);
            arrow = itemView.findViewById(R.id.arrow);
            countryId = itemView.findViewById(R.id.countryId);
            rate = itemView.findViewById(R.id.rate);
            difference = itemView.findViewById(R.id.difference);
        }
    }

    public ValuteAdapter(MainActivity mainActivity, ArrayList<Valute> arrayList) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,
                parent, false);
        return new RecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        Valute recyclerViewItem = arrayList.get(position);

        holder.countryFlag.setImageResource(recyclerViewItem.getCountryFlag());
        holder.arrow.setImageResource(recyclerViewItem.getArrow());
        holder.countryId.setText(recyclerViewItem.getCountryId());
        holder.rate.setText(Double.toString(recyclerViewItem.getCurrentRate()));
        holder.difference.setText(Double.toString(recyclerViewItem.getDifference()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
