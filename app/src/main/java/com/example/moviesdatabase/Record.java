package com.example.moviesdatabase;

public class Record {


    private String title;
    private String yearReleased;
    private String description;
    private Integer movieImage;

    public Record() {
    }

    public Record(String title, String yearReleased, String description, int movieImage) {
        this.title = title;
        this.yearReleased = yearReleased;
        this.description = description;
        this.movieImage = movieImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(int movieImage) {
        this.movieImage = movieImage;
    }

    @Override
    public String toString() {
        return "Record{" +
                "title='" + title + '\'' +
                ", yearReleased='" + yearReleased + '\'' +
                ", movieImage=" + movieImage +
                '}';
    }
}
