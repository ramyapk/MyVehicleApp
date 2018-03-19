package com.example.ramya.myvehicleapp.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.ramya.myvehicleapp.models.UserProfile;
import com.example.ramya.myvehicleapp.models.Vehicles;

/**
 * A classtoimplement Content Provider functionalities
 */
public class ExampleContentProvider extends ContentProvider {

    public static String AUTHORITY = "com.example.ramya.myvehicleapp";
    private SQLiteDatabase database;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, UserProfile.USER_URI_PATH, UserProfile.USER_URI_ID);
        uriMatcher.addURI(AUTHORITY, Vehicles.VEHICLE_URI_PATH, Vehicles.VEHICLE_URI_ID);
    }

    @Override
    public boolean onCreate() {

        ExampleOpenHelper openHelper = new ExampleOpenHelper(getContext());
        database = openHelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        int tableId = uriMatcher.match(uri);
        switch (tableId) {
            case UserProfile.USER_URI_ID :
                cursor = database.query("user", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case Vehicles.VEHICLE_URI_ID :
                cursor = database.query("vehicles", projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int tableId  = uriMatcher.match(uri);

        switch (tableId) {
            case UserProfile.USER_URI_ID :
                database.insert("user",null, values);
                break;
            case Vehicles.VEHICLE_URI_ID :
                database.insert("vehicles", null, values);
                break;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount = 0;
        int tableId  = uriMatcher.match(uri);

        switch (tableId) {
            case UserProfile.USER_URI_ID :
                rowCount = database.delete("user", selection,selectionArgs);
                break;
            case Vehicles.VEHICLE_URI_ID :
                rowCount = database.delete("vehicles", selection,selectionArgs);
                break;
        }
        return rowCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount = 0;
        int tableId  = uriMatcher.match(uri);

        switch (tableId) {
            case UserProfile.USER_URI_ID :
                rowCount = database.update("user", values, selection,selectionArgs);
                break;
            case Vehicles.VEHICLE_URI_ID :
                rowCount = database.update("vehicles", values, selection,selectionArgs);
                break;
        }
        return rowCount;
    }
}
