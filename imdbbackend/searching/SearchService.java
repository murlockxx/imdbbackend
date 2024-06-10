package com.imdbv1.imdbbackend.searching;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imdbv1.imdbbackend.actors.Actor;
import com.imdbv1.imdbbackend.actors.ActorRepository;
import com.imdbv1.imdbbackend.movies.Movie;
import com.imdbv1.imdbbackend.movies.MovieRepository;

@Service
public class SearchService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    // Method to search movies by name containing the given query string
    public List<Movie> search(String query) {
        // Fetches movies whose names contain the provided query string
    
        return movieRepository.findByMovieNameContaining(query);
    }

    public List<Movie> searchForMovie(String query) {
        return movieRepository.findByMovieNameContaining(query);
    }

    public List<Actor> searchForActor(String query) {
        return actorRepository.findByNameContaining(query);
    }
}
