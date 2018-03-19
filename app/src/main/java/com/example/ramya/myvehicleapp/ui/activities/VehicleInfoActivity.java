package com.example.ramya.myvehicleapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ramya.myvehicleapp.R;
import com.example.ramya.myvehicleapp.adapters.CustomAdapter;
import com.example.ramya.myvehicleapp.database.ExampleDao;
import com.example.ramya.myvehicleapp.interfaces.OnItemClickListener;
import com.example.ramya.myvehicleapp.models.UserProfile;
import com.example.ramya.myvehicleapp.models.Vehicles;
import com.example.ramya.myvehicleapp.util.Constants;

import java.util.ArrayList;

public class VehicleInfoActivity extends AppCompatActivity {

    RecyclerView rcvVehicleDetails;
    TextView welcome, tvDetails;
    String[] picArray = {"https://d30y9cdsu7xlg0.cloudfront.net/png/72-200.png"};
    Button btnAddVehicle;

    UserProfile user = new UserProfile();
    ArrayList<Vehicles> vehiclesArrayList = new ArrayList<>();

    String email;

    ExampleDao exampleDao;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);

        welcome = findViewById(R.id.tv_welcome_user);
        tvDetails = findViewById(R.id.tv_details_text);
        btnAddVehicle = findViewById(R.id.btn_addVehicle);
        rcvVehicleDetails = findViewById(R.id.activity_vehicle_info_rcv_vehicles);

        exampleDao = new ExampleDao(getApplicationContext());

        email = getIntent().getStringExtra(Constants.KEY_USER_EMAIL);

        user = exampleDao.getUser(email);

        welcome.setText(getResources().getString(R.string.welcome, user.getFirstName(), user.getLastName()));

        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleInfoActivity.this, AddvehicleActivity.class);
                intent.putExtra(Constants.KEY_USER_EMAIL, email);
                startActivity(intent);
                rcvVehicleDetails.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        vehiclesArrayList = exampleDao.getVehicles(email);

        if (vehiclesArrayList.size() == 0) {
            tvDetails.setText(R.string.no_vehicles);
            rcvVehicleDetails.setVisibility(View.GONE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(VehicleInfoActivity.this);
        rcvVehicleDetails.setLayoutManager(layoutManager);
        adapter = new CustomAdapter(picArray, vehiclesArrayList, VehicleInfoActivity.this);
        rcvVehicleDetails.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(VehicleInfoActivity.this, ViewDetailsActivity.class);
                //Passing vehicle object to VehicleDetailsActivity to view details
                intent.putExtra(Constants.KEY_VEHICLE_OBJECT, vehiclesArrayList.get(position));
                startActivity(intent);
            }
        });

        //Swipe to delete in RecyclerView

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    exampleDao.deleteVehicle(vehiclesArrayList.get(position).getVehicleId());
                    vehiclesArrayList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rcvVehicleDetails);
    }
}
