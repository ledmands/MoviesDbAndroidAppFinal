package com.example.moviesdatabase;

import android.provider.BaseColumns;

public class FeedReaderContract {

    // To prevent someone from accidentally instantiating the contract class
    // make the constructor private

    private FeedReaderContract() {}

    // Inner class that defines the table contents
    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_RELEASE_YEAR = "releaseYear";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE_RESOURCE_ID = "imageResourceId";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                        FeedEntry.COLUMN_NAME_RELEASE_YEAR + " TEXT," +
                        FeedEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        FeedEntry.COLUMN_NAME_IMAGE_RESOURCE_ID + " INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    }
}
