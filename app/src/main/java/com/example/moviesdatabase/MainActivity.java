package com.example.moviesdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.database.sqlite.SQLiteDatabaseKt;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FeedReaderDbHelper databaseHelper;
    private ListView listView;
    private Button btnInsertRecord;
    private Button btnRefresh;

    private ArrayList<Record> recordList;
    private SQLiteDatabase db;

    private String title;
    private String yearReleased;
    private String description;
    private Integer movieImage;

    private RecordAdapter recordAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new FeedReaderDbHelper(this);
        btnInsertRecord = findViewById(R.id.btnInsertRecord);
        listView = findViewById(R.id.listView);
        btnRefresh = findViewById(R.id.btnRefresh);

        recordList = new ArrayList<>();

        // need to create db, get cursor, then set a new record for each row
        db = getBaseContext().openOrCreateDatabase("movies", MODE_PRIVATE, null);

        // this is for setup
        //databaseHelper.insert("The Iron Giant", "1999", "A young boy befriends a giant robot
        // from outer space that a paranoid government agent wants to destroy.");
        //databaseHelper.insert("The Waterboy", "1995");
        //databaseHelper.insert("Return of the King", "2003");

////// -- TO ENTER IMAGES, USE THIS METHOD -- //////
        // This will need to be manually updated every time a new movie is added, as there
        // is currently no way to automatically attach the resource ID to the record.
        // Could I extract the drawable ID and then pass it into the database to match the movie?

        //databaseHelper.insertImageResourceId();

        Cursor cursor = databaseHelper.getData();

        while (cursor.moveToNext()) {

            //Log.d(TAG, "currentRecord: " + currentRecord);

            // Set each field
            title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            yearReleased = cursor.getString(cursor.getColumnIndexOrThrow("releaseYear"));
            description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            movieImage = cursor.getInt(cursor.getColumnIndexOrThrow("imageResourceId"));

            Log.d(TAG, "looping cursor movie image: " + movieImage);

            // Now get the appropriate data and send it to the record list?
            recordList.add(new Record(title, yearReleased, description, movieImage));
        }

        // Then we need the record adapter set
        recordAdapter = new RecordAdapter(MainActivity.this, R.layout.list_item_record, recordList);

        // Then set listview
        listView.setAdapter(recordAdapter);

        btnInsertRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity
                Intent insertNewRecord = new Intent(MainActivity.this, InsertRecordActivity.class);

                // start activity
                startActivity(insertNewRecord);
            }
        });

        // This refreshes the view after a new record has been inserted, as it will not do so automatically
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity
                Intent refresh = new Intent(MainActivity.this, MainActivity.class);

                // start activity
                startActivity(refresh);
            }
        });

        // Long press for description
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Create a record at position i
                Record record = recordList.get(i);
                //Log.d(TAG, "onItemLongClick: " + record.getDescription());

                // Then display the description using a layout inflater
                // get the desc from db and pass to method
                showDescription(record.getDescription(), record.getTitle());

                return false;
            }
        });

        //displayRecords();
    } // End onCreate()

    private void showDescription(String description, String title) {
        //Log.d(TAG, "showDescription before anything else happens: ");
        AlertDialog.Builder descriptionDialog = new AlertDialog.Builder(this);
        //Log.d(TAG, "showDescription after  new AlertDialog.Builder: ");

        LayoutInflater inflater = getLayoutInflater();
        //Log.d(TAG, "showDescription after getLayoutInflater: ");

        View descriptionView = inflater.inflate(R.layout.dialog_description, null);
        //Log.d(TAG, "showDescription after inflater.inflate(R.layout.dialog_description, null);: ");

        descriptionDialog.setView(descriptionView);
        //Log.d(TAG, "showDescription after setView(): ");

        TextView txtViewDialogDescription = descriptionView.findViewById(R.id.txtViewDialogDescription);
        //Log.d(TAG, "showDescription after findViewById: ");
        //Log.d(TAG, "txtViewDialogDescription: " + txtViewDialogDescription);
        // So the TextView was being set to null because it was not being included in the findViewById().
        // Needs to be prefaced with the current view and a . (descriptionView.)

        // This is where the program was running into trouble
        //Log.d(TAG, "showDescription before setText(): " + description);

        txtViewDialogDescription.setText(description);
        //Log.d(TAG, "showDescription after setText: ");

        descriptionDialog.setTitle(String.format("%s Synopsis", title));

        AlertDialog dialog = descriptionDialog.create();
        dialog.show();
    }

    private void displayRecords() {

        String currentMovieName;
        String currentMovieYearReleased;
        String currentMovieId;
        Integer currentMovieResId;

        // to help shorten syntax
        Cursor cursor = databaseHelper.getData();

        // Loop through records
        //while (databaseHelper.getData().moveToNext()) {
        while (cursor.moveToNext()) { // if cursor variable exists

            currentMovieName = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            currentMovieYearReleased = cursor.getString(cursor.getColumnIndexOrThrow("releaseYear"));
            currentMovieId = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            currentMovieResId = cursor.getInt(cursor.getColumnIndexOrThrow("imageResourceId"));

            Log.i(TAG, "displayRecords: title: " + currentMovieName);
            Log.i(TAG, "displayRecords: yearReleased " + currentMovieYearReleased);
            Log.i(TAG, "displayRecords: id " + currentMovieId);
            Log.i(TAG, "displayRecords: ResId " + currentMovieResId);
        }
    }
}