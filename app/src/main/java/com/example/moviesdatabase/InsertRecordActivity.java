package com.example.moviesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InsertRecordActivity extends AppCompatActivity {

    TextView txtNewMovieTitle;
    TextView txtNewMovieYear;
    TextView txtNewMovieDescription;
    Button btnInsertNewMovie;

    FeedReaderDbHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);

        txtNewMovieTitle = findViewById(R.id.txtNewMovieTitle);
        txtNewMovieYear = findViewById(R.id.txtNewMovieYear);
        txtNewMovieDescription = findViewById(R.id.txtNewMovieDescription);
        btnInsertNewMovie = findViewById(R.id.btnInsertNewMovie);

        databaseHelper = new FeedReaderDbHelper(this);

        btnInsertNewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHelper.insert(txtNewMovieTitle.getText().toString(), txtNewMovieYear.getText().toString(), txtNewMovieDescription.getText().toString());

                // Return to main activity
                finish();
            }
        });
    }
}