package com.imdbv1.imdbbackend.watchlistops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
    @Query("SELECT w.movieId FROM Watchlist w WHERE w.email = :email")
    List<Long> findAllMovieIdsByEmail(@Param("email") String email);
}
