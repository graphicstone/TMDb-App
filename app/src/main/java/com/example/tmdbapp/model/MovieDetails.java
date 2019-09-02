package com.example.tmdbapp.model;

public class MovieDetails
{
    private String orginal_title;
    private String overview;
    private String release_date;
    private String vote_average;
    private String poster_path;

    public MovieDetails(String orginal_title, String overview, String release_date, String vote_average, String poster_path) {
        this.orginal_title = orginal_title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
    }

    public String getOrginal_title() {
        return orginal_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

}
