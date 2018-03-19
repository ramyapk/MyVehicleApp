package com.example.ramya.myvehicleapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ramya.myvehicleapp.models.UserProfile;
import com.example.ramya.myvehicleapp.models.Vehicles;

public class ExampleOpenHelper extends SQLiteOpenHelper {

    public ExampleOpenHelper(Context context) {
        super(context, "vehicleApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserProfile.CREATE_TABLE);
        sqLiteDatabase.execSQL(Vehicles.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}