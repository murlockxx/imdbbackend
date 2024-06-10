package com.imdbv1.imdbbackend.movies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{

    List<Movie> findByMovieNameContaining(String name);

    Movie findByMovieName(String name);
    
    
}
