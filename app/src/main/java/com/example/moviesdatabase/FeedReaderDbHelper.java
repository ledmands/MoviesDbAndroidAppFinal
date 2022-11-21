package com.example.moviesdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "FeedReaderHelper";

    // If database schema is changed, you must increment db version
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Movies.db";



    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedReaderContract.FeedEntry.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(FeedReaderContract.FeedEntry.SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // Put information into the database
    public long insert(String title, String yearReleased, String description) {

        // Gets data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_RELEASE_YEAR, yearReleased);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DESCRIPTION, description);

        // Insert new row, returning the primary key value of the new row
        return db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
    }

    public Cursor getData() {

        // Gets data repository in read mode
        SQLiteDatabase db = getReadableDatabase();
        Log.d(TAG, "getData(): " + getReadableDatabase());

        String query = "SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d(TAG, "query: " + query);

        return cursor;
    }

    // This is currently how I know how to set the resource ID. The image must already be in the
    // drawables folder, then it is set into the database using the primary key of the record.
    // Once the resource ID is in the database, then it can be set using the record adapter in
    // order to attach the resource ID every time it is displayed.
    public void insertImageResourceId() {
        SQLiteDatabase db = getWritableDatabase();

        // get the requisite resource id
        Integer resourceId = R.drawable.heavy_metal;
        Integer columnId = 5;
        // execute query
        db.execSQL(String.format("UPDATE movies SET %s = %s WHERE _id = %s", FeedReaderContract.FeedEntry.COLUMN_NAME_IMAGE_RESOURCE_ID, resourceId, columnId));
    }

}
