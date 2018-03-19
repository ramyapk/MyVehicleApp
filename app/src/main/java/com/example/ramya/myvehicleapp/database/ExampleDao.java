package com.example.ramya.myvehicleapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ramya.myvehicleapp.models.UserProfile;
import com.example.ramya.myvehicleapp.models.Vehicles;

import java.util.ArrayList;

public class ExampleDao {
    private SQLiteDatabase database;

    public ExampleDao(Context context) {
        SQLiteOpenHelper helper = new ExampleOpenHelper(context);
        database = helper.getWritableDatabase();
    }

    /**
     * Method to insert a new user into the user database
     * @param fname First Name of the user
     * @param lname Last Name of the user
     * @param email Email ID of the user
     * @param password Password for the user
     */
    public void insertUser(String fname, String lname, String email, String password) {
        ContentValues values = new ContentValues();

        values.put("fname", fname);
        values.put("lname", lname);
        values.put("email", email);
        values.put("password", password);
        database.insert("user", null, values);
    }

    /**
     * Method to insert a new vehicle for the user into the vehicle database
     * @param username Email ID of the user
     * @param vehicleId ID of the vehicle
     * @param vehicleName Name of the vehicle
     * @param vehicleType Type of the vehicle (two-wheeler, four-wheeler, etc.
     * @param manufacturer Vehicle Manufacturer
     * @param color Color of the vehicle
     */
    public void insertVehicle(String username, int vehicleId, String vehicleName, String vehicleType, String manufacturer, String color) {
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("vehicleid", vehicleId);
        values.put("vehicleName", vehicleName);
        values.put("vehicleType", vehicleType);
        values.put("manufacturer", manufacturer);
        values.put("color", color);

        database.insert("vehicles", null, values);
    }

    /**
     * Method to find the user details with the given Email ID
     * @param email Email ID of he user
     * @return UserProfile oblect containing user details
     */
    public UserProfile getUser(String email) {
        UserProfile user = new UserProfile();
        String selection = "email=?";
        String selectionArgs[] = {email};

        //select * from user where email="email"
        Cursor cursor = database.query("user", null, selection, selectionArgs, null, null, null);

        cursor.moveToFirst();

        boolean isUserValid = false;
        while (!cursor.isAfterLast()) {
            String fname = cursor.getString(cursor.getColumnIndex("fname"));
            String lname = cursor.getString(cursor.getColumnIndex("lname"));
            String userEmail = cursor.getString(cursor.getColumnIndex("email"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            user.setFirstName(fname);
            user.setLastName(lname);
            user.setEmail(userEmail);
            user.setPassword(password);

            isUserValid = true;

            cursor.moveToNext();
        }

        cursor.close();
        if (isUserValid) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * A method to get all the vehicle details for the given user
     * @param username Email ID of the user
     * @return An ArrayList of Vehicles objects
     */
    public ArrayList<Vehicles> getVehicles(String username) {

        ArrayList<Vehicles> vehiclesArrayList = new ArrayList<>();

        String selection = "username=?";
        String selectionArgs[] = {username};

        //select * from vehicles where username = "username"
        Cursor cursor = database.query("vehicles", null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        if (cursor == null) {
            Vehicles vehicles =  null;
            vehiclesArrayList.add(vehicles);
        } else {
            while (!cursor.isAfterLast()) {
                Vehicles vehicles = new Vehicles();
                int id = cursor.getInt(cursor.getColumnIndex("vehicleid"));
                String name = cursor.getString(cursor.getColumnIndex("vehicleName"));
                String type = cursor.getString(cursor.getColumnIndex("vehicleType"));
                String manufacturer = cursor.getString(cursor.getColumnIndex("manufacturer"));
                String color = cursor.getString(cursor.getColumnIndex("color"));

                vehicles.setVehicleId(id);
                vehicles.setVehicleName(name);
                vehicles.setVehicleType(type);
                vehicles.setManufacturer(manufacturer);
                vehicles.setColour(color);

                vehiclesArrayList.add(vehicles);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return vehiclesArrayList;
    }

    /**
     * A method to delete vehicle details for the given vehicle ID
     * @param vehicleId Vehicle ID
     */
    public void deleteVehicle (int vehicleId) {
        String selection = "vehicleid=?";
        String selectionArgs[] = {vehicleId+""};

        database.delete("vehicles", selection, selectionArgs);
    }
}