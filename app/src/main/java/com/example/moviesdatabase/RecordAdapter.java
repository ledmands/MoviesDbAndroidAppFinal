package com.example.moviesdatabase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecordAdapter extends ArrayAdapter {

    private static final String TAG = "RecordAdapter";

    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Record> movieEntries;

    public RecordAdapter(@NonNull Context context, int resource, List<Record> movieEntries) {
        super(context, resource, movieEntries);

        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.movieEntries = movieEntries;
    }

    // Automatically called for each record that we tie to data
    public View getView(int position, View convertView, ViewGroup parent) {

        // The Layout Inflater takes data and ties it to layout
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Now get the information from the database
        Record record = movieEntries.get(position);

        viewHolder.titleText.setText(record.getTitle());
        viewHolder.yearReleasedText.setText(record.getYearReleased());
        //Log.d(TAG, "gettitle: " + record.getTitle());
        //Log.d(TAG, "getyear: " + record.getYearReleased());
        //Log.d(TAG, "getdesc: " + record.getDescription());
        if (record.getMovieImage() == 0) {
            //Log.d(TAG, "getmovieimage: " + record.getMovieImage());
            // If the movie image resource does not exist or has not been set
            // in the database yet, do nothing
        }
        else {
            viewHolder.movieImage.setImageResource(record.getMovieImage());
        }

        //Log.d(TAG, "getMovieImage: " + record.getMovieImage());
        return convertView;

    } // end getView

    private class ViewHolder {

        final TextView titleText;
        final TextView yearReleasedText;
        final ImageView movieImage;

        public ViewHolder(View view) {
            titleText = view.findViewById(R.id.viewTitleText);
            yearReleasedText = view.findViewById(R.id.viewYearReleasedText);
            movieImage = view.findViewById(R.id.viewMovieImage);
        }
    } // end viewholder class
}