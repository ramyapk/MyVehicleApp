package com.example.ramya.myvehicleapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramya.myvehicleapp.R;
import com.example.ramya.myvehicleapp.database.ExampleDao;
import com.example.ramya.myvehicleapp.util.Constants;

public class AddvehicleActivity extends AppCompatActivity {

    EditText etVehicleId, etVehicleName, etVehicleType, etManufacturer, etColor;
    Button btnAddVehicle, btnDone;

    ExampleDao exampleDao;

    int id;
    String name, type, manufacturer, color;
    String userName;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvehicle);

        exampleDao = new ExampleDao(getApplicationContext());

        Intent intent = getIntent();
        userName = intent.getStringExtra(Constants.KEY_USER_EMAIL);

        etVehicleId = findViewById(R.id.activity_addvehicle_et_vehicleID);
        etVehicleName = findViewById(R.id.activity_addvehicle_et_vehicleName);
        etVehicleType = findViewById(R.id.activity_addvehicle_et_vehicleType);
        etManufacturer = findViewById(R.id.activity_addvehicle_et_manufacturer);
        etColor = findViewById(R.id.activity_addvehicle_et_vehicleColor);
        btnAddVehicle = findViewById(R.id.activity_addvehicle_btn_addVehicle);
        btnDone = findViewById(R.id.activity_addvehicle_btn_done);

        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails(userName);
                refresh();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails(userName);
                finish();
            }
        });
    }

    public void saveDetails(String userName) {
        name = etVehicleName.getText().toString();
        type = etVehicleType.getText().toString();
        manufacturer = etManufacturer.getText().toString();
        color = etColor.getText().toString();

        if (!(name.isEmpty() || type.isEmpty() || manufacturer.isEmpty() || color.isEmpty() || etVehicleId.getText() == null)) {
            id = Integer.parseInt(etVehicleId.getText().toString());
            exampleDao.insertVehicle(userName, id, name, type, manufacturer, color);
        } else {
            Toast.makeText(getApplicationContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
        }
    }

    public void refresh() {
        etVehicleId.setText(R.string.null_text);
        etVehicleName.setText(R.string.null_text);
        etVehicleType.setText(R.string.null_text);
        etManufacturer.setText(R.string.null_text);
        etColor.setText(R.string.null_text);
    }
}
