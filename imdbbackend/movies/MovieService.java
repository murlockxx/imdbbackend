package com.imdbv1.imdbbackend.movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public boolean addMovie(Movie movie) {
        if(!movieRepository.existsById(movie.getId())){
            movieRepository.save(movie);
            return true;
        }
        else{
            return false;

        }
        
    }

    public List<Movie> getAllMovies() {
       return movieRepository.findAll();
    }

    public Movie getDetails(String name) {
        return movieRepository.findByMovieName(name);
    }
    
}
