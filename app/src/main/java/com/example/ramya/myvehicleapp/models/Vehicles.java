package com.example.ramya.myvehicleapp.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.ramya.myvehicleapp.database.ExampleContentProvider;

/**
 * Created by LENONO on 3/12/2018.
 */

public class Vehicles implements Parcelable {

    public static String CREATE_TABLE = "CREATE TABLE vehicles (username TEXT, vehicleid INTEGER, vehicleName TEXT, vehicleType TEXT, manufacturer TEXT, color TEXT);";
    public static String VEHICLE_URI_PATH = "vehicle";
    public static final String vehicleUriString = "content://" + ExampleContentProvider.AUTHORITY + VEHICLE_URI_PATH;
    public static final Uri vehivleUri = Uri.parse(vehicleUriString);
    public static final int VEHICLE_URI_ID = 1;

    private int vehicleId;
    private String vehicleName;
    private String vehicleType;
    private String manufacturer;
    private String colour;

    public Vehicles(int vehicleId, String vehicleName, String vehicleType, String manufacturer, String colour) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.colour = colour;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.vehicleId);
        dest.writeString(this.vehicleName);
        dest.writeString(this.vehicleType);
        dest.writeString(this.manufacturer);
        dest.writeString(this.colour);
    }

    public Vehicles() {
    }

    protected Vehicles(Parcel in) {
        this.vehicleId = in.readInt();
        this.vehicleName = in.readString();
        this.vehicleType = in.readString();
        this.manufacturer = in.readString();
        this.colour = in.readString();
    }

    public static final Parcelable.Creator<Vehicles> CREATOR = new Parcelable.Creator<Vehicles>() {
        @Override
        public Vehicles createFromParcel(Parcel source) {
            return new Vehicles(source);
        }

        @Override
        public Vehicles[] newArray(int size) {
            return new Vehicles[size];
        }
    };
}
