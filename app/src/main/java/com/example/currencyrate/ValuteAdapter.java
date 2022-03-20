package com.example.currencyrate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ValuteAdapter extends RecyclerView.Adapter<ValuteAdapter.RecyclerViewViewHolder>  {

    private ArrayList<Valute> arrayList;

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements Serializable {

        public Context context;
        public ImageView countryFlag;
        public ImageView arrow;
        public TextView countryId;
        public TextView rate;
        public TextView difference;
        public TextView textView;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.countryFlag);
            arrow = itemView.findViewById(R.id.arrow);
            countryId = itemView.findViewById(R.id.countryId);
            rate = itemView.findViewById(R.id.rate);
            difference = itemView.findViewById(R.id.difference);

            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    TextView countryID = itemView.findViewById(R.id.countryId);
                    TextView countryRate = itemView.findViewById(R.id.rate);
                    String id = (String) countryID.getText();
                    String rate = countryRate.getText().toString();
                    Log.i("rate11", countryRate.getText().toString());
                    Intent intent = new Intent(view.getContext(), ConverterActivity.class);
                    intent.putExtra("countryID", id);
                    intent.putExtra("rate", rate);
                    view.getContext().startActivity(intent);
                }
            });
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
        DecimalFormat decimalFormat = new DecimalFormat("#.####");

        if (recyclerViewItem.getCountryId().equals("TRY")) {
            holder.countryFlag.setImageResource(R.drawable.t_ry);
        } else {
            holder.countryFlag.setImageResource(recyclerViewItem.getCountryFlag());
        }
        holder.countryId.setText(recyclerViewItem.getCountryId());
        holder.rate.setText(decimalFormat.format(recyclerViewItem.getCurrentRate()));
        holder.difference.setText(decimalFormat.format(recyclerViewItem.getDifference()));
        if (recyclerViewItem.getDifference() > 0) {
            holder.arrow.setImageResource(R.drawable.up_arrow);
            holder.difference.setTextColor(Color.parseColor("#3FDB23"));
        } else {
            holder.arrow.setImageResource(R.drawable.down_arrow);
            holder.difference.setTextColor(Color.parseColor("#DB2323"));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
