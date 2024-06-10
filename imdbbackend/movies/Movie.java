package com.imdbv1.imdbbackend.movies;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movieName;
    private int point;
    private int year; // can be changed to String if needed
    private String trailerUrl;
    private String photoUrl;
    private String explanation;
    
    @ElementCollection
    private List<String> leadingPlayer;

    public Movie() {
    }

    public Movie(Long id, String movieName, int point, int year, String trailerUrl, String photoUrl,
                 String explanation, List<String> leadingPlayer) {
        this.id = id;
        this.movieName = movieName;
        this.point = point;
        this.year = year;
        this.trailerUrl = trailerUrl;
        this.photoUrl = photoUrl;
        this.explanation = explanation;
        this.leadingPlayer = leadingPlayer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<String> getLeadingPlayer() {
        return leadingPlayer;
    }

    public void setLeadingPlayer(List<String> leadingPlayer) {
        this.leadingPlayer = leadingPlayer;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", movieName=" + movieName + ", point=" + point + ", year=" + year + ", trailerUrl="
                + trailerUrl + ", photoUrl=" + photoUrl + ", explanation=" + explanation + ", leadingPlayer="
                + leadingPlayer + "]";
    }
}
