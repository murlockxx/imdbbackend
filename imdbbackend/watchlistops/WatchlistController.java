package com.imdbv1.imdbbackend.watchlistops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.imdbv1.imdbbackend.movies.Movie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1/watchlist")
public class WatchlistController {

    private static final Logger logger = LogManager.getLogger(WatchlistController.class);

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping("/getUserWatchlist")
    public ResponseEntity<List<Movie>> getWatchlist(HttpServletRequest request) {
        String sessionId = Arrays.stream(request.getCookies())
                                .filter(c -> "session_id".equals(c.getName()))
                                .findFirst()
                                .map(Cookie::getValue)
                                .orElse("No session cookie");

        logger.info("Session ID: {}", sessionId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        logger.info("Fetching watchlist for user: {}", email);

        if (authentication instanceof AnonymousAuthenticationToken) {
            logger.warn("Request is from an anonymous user.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(watchlistService.getWatchlistByEmail(email));
    }


    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody Long movieId,@RequestBody String email) {
        

        try {
            watchlistService.addMovieToWatchlist(email, movieId);
            logger.info("Movie added to watchlist for user: {}", email);
            return ResponseEntity.ok("Movie added to watchlist successfully!");
        } catch (Exception e) {
            logger.error("Error adding movie to watchlist for user: {}", email, e);
            return ResponseEntity.internalServerError().body("Failed to add movie to watchlist.");
        }
    }
}
