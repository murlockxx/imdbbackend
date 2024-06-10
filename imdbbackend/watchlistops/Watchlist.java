package com.imdbv1.imdbbackend.watchlistops;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String email;
    private Long movieId;


    public Watchlist(Long id, String email, Long movieId) {
        this.id = id;
        this.email = email;
        this.movieId = movieId;
    }


    public Watchlist() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getMovieId() {
        return movieId;
    }
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }


    

    
}
