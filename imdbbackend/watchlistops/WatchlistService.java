package com.imdbv1.imdbbackend.watchlistops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imdbv1.imdbbackend.movies.Movie;
import com.imdbv1.imdbbackend.movies.MovieRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getWatchlistByEmail(String email) {
        List<Long> movieIds = watchlistRepository.findAllMovieIdsByEmail(email);
        List<Movie> movies = new ArrayList<>();
        movieIds.forEach(id -> movieRepository.findById(id).ifPresent(movies::add));
        return movies;
    }

    public void addMovieToWatchlist(String email, Long movieId) {
        Watchlist watchlist = new Watchlist(null, email, movieId);
        watchlistRepository.save(watchlist);
    }
}
