package model;

public class Movie {

    private String title;
    private String genre;
    private double vote_average;
    private int year;
    private String overview;
    private int imageResId;

    public Movie(String title,
                 String genre,
                 double vote_average,
                 int year,
                 String overview,
                 int imageResId) {

        this.title = title;
        this.genre = genre;
        this.vote_average = vote_average;
        this.year = year;
        this.overview = overview;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getYear() {
        return year;
    }

    public String getOverview() {
        return overview;
    }

    public int getImageResId() {
        return imageResId;
    }
}