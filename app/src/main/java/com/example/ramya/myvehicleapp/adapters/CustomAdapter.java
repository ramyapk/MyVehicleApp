package com.example.ramya.myvehicleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramya.myvehicleapp.R;
import com.example.ramya.myvehicleapp.interfaces.OnItemClickListener;
import com.example.ramya.myvehicleapp.models.Vehicles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A custom adapter class for the RecyclerView used to print the vehicle details
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Vehicles> vehiclesArrayList;
    private String[] imageArray;
    private Context context;
    private OnItemClickListener listener;

    /**
     * A ViewHolder class that describes an item's view and related infrmation
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVehicleID;
        TextView tvVehicleName;
        ImageView ivVehicleImage;
        View itemView;

        private ViewHolder(View view) {
            super(view);
            this.itemView = view;
            this.tvVehicleID = view.findViewById(R.id.list_item_tv_vehicleID);
            this.tvVehicleName = view.findViewById(R.id.list_item_tv_vehicleName);
            this.ivVehicleImage = view.findViewById(R.id.list_item_iv_vehicleImage);
        }

        private void setOnClickListener(final int position, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(position);
                }
            });
        }
    }

    public CustomAdapter(String[] imageArray, ArrayList<Vehicles> vehiclesArrayList, Context context) {
        this.imageArray = imageArray;
        this.vehiclesArrayList = vehiclesArrayList;
        this.context = context;
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CustomAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder != null) {

            //Using picasso to load the image into ImageView
            Picasso.with(context)
                    .load(imageArray[0])
                    .resize(350, 350)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.ivVehicleImage);

            //Setting vehicle information intolist items TextViews
            holder.tvVehicleID.setText(Integer.toString(vehiclesArrayList.get(position).getVehicleId()));
            holder.tvVehicleName.setText(vehiclesArrayList.get(position).getVehicleName());

            holder.setOnClickListener(position, listener);
        }
    }

    @Override
    public int getItemCount() {
        return vehiclesArrayList.size();
    }
}