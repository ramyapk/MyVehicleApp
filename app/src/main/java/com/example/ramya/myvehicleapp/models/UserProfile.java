package com.example.ramya.myvehicleapp.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.ramya.myvehicleapp.database.ExampleContentProvider;

import java.util.ArrayList;

/**
 * Created by LENONO on 3/12/2018.
 */

public class UserProfile implements Parcelable {

    public static String CREATE_TABLE = "CREATE TABLE user (fname TEXT, lname TEXT, email TEXT, password TEXT);";
    public static String USER_URI_PATH = "user";
    public static final String userUriString = "content://" + ExampleContentProvider.AUTHORITY + USER_URI_PATH;
    public static final Uri userUri = Uri.parse(userUriString);
    public static final int USER_URI_ID = 0;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Vehicles> vehicles;

    public ArrayList<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile() {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.password = null;
        this.vehicles = null;
    }

    public UserProfile(String firstName, String lastName, String email, String password, ArrayList<Vehicles> vehicles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.vehicles = vehicles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeTypedList(this.vehicles);
    }

    private UserProfile(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.vehicles = in.createTypedArrayList(Vehicles.CREATOR);
    }

    public static final Parcelable.Creator<UserProfile> CREATOR = new Parcelable.Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel source) {
            return new UserProfile(source);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };
}
