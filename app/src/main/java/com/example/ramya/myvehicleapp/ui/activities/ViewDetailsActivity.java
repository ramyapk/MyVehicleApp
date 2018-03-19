package com.example.ramya.myvehicleapp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ramya.myvehicleapp.R;
import com.example.ramya.myvehicleapp.models.Vehicles;
import com.example.ramya.myvehicleapp.util.Constants;

public class ViewDetailsActivity extends AppCompatActivity {
    TextView tvVehicleId, tvVehicleName, tvManufaturer, tvVehicleType, tvVehicleColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        tvVehicleId = findViewById(R.id.activity_view_details_tv_vehicleID);
        tvVehicleName = findViewById(R.id.activity_view_details_tv_vehicleName);
        tvVehicleType = findViewById(R.id.activity_view_details_tv_vehicleType);
        tvManufaturer = findViewById(R.id.activity_view_details_tv_manufacturer);
        tvVehicleColor = findViewById(R.id.activity_view_details_tv_vehicleColor);

        //Getting vehicle details from VehicleInfoActivity
        Vehicles vehicle = getIntent().getParcelableExtra(Constants.KEY_VEHICLE_OBJECT);

        //Displaying Vehicle Information in TextViews
        tvVehicleId.setText(Integer.toString(vehicle.getVehicleId()));
        tvVehicleName.setText(vehicle.getVehicleName());
        tvVehicleType.setText(vehicle.getVehicleType());
        tvManufaturer.setText(vehicle.getVehicleType());
        tvVehicleColor.setText(vehicle.getColour());
    }
}
