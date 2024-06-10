package com.imdbv1.imdbbackend.searching;

import java.util.List;

import com.imdbv1.imdbbackend.actors.Actor;
import com.imdbv1.imdbbackend.movies.Movie;

public class SearchResultDTO {
    private List<Movie> movies;
    private List<Actor> actors;

    // Constructors
    public SearchResultDTO() {
    }

    public SearchResultDTO(List<Movie> movies, List<Actor> actors) {
        this.movies = movies;
        this.actors = actors;
    }

    // Getters and setters
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
