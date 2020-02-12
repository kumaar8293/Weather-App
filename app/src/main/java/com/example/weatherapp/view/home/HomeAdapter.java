package com.example.weatherapp.view.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.datamodel.Weather;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.WeaterViewHolder> {
    private static final String TAG = "HomeAdapter";


    private Context context;
    private ArrayList<Weather> weatherList;
    private OnItemClickListener onItemClickListener;

    HomeAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    void setData(ArrayList<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public WeaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_weather, parent, false);
        return new WeaterViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeaterViewHolder holder, int position) {
        holder.tvDay.setText(weatherList.get(position).getLocation().getName());
        holder.tvWeatherType.setText(weatherList.get(position).getCurrent().getWeatherDescriptions().get(0));
        holder.tvDaysCelcius.setText(("" + weatherList.get(position).getCurrent().getHumidity()));
        Glide.with(context).load(weatherList.get(position).getCurrent().getWeatherIcons().get(0)).into(holder.ivWeatherType);

    }

    @Override
    public int getItemCount() {
        if (weatherList == null)
            return 0;
        return weatherList.size();
    }

    class WeaterViewHolder extends RecyclerView.ViewHolder {
        ImageView ivWeatherType;
        TextView tvWeatherType, tvDay, tvDaysCelcius;

        WeaterViewHolder(@NonNull View itemView) {
            super(itemView);

            initialiseUIElements();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        if (onItemClickListener != null)
                            onItemClickListener.onClick(weatherList.get(getAdapterPosition()));
                    }
                }
            });
        }

        private void initialiseUIElements() {
            tvDay = itemView.findViewById(R.id.tv_day);
            tvDaysCelcius = itemView.findViewById(R.id.tv_daysCelcius);
            tvWeatherType = itemView.findViewById(R.id.tv_weatherType);
            ivWeatherType = itemView.findViewById(R.id.iv_weatherType);
        }
    }

    public interface OnItemClickListener {
        void onClick(Weather weather);
    }
}
