package com.imdbv1.imdbbackend.searching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imdbv1.imdbbackend.actors.Actor;
import com.imdbv1.imdbbackend.movies.Movie;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/searching/{query}")
    public SearchResultDTO getMethodName(@PathVariable String query) {
        List<Movie> movieQueryResults = searchService.searchForMovie(query);
        List<Actor> actorQueryResults = searchService.searchForActor(query);

        SearchResultDTO totalResult = new SearchResultDTO(movieQueryResults, actorQueryResults);

        return totalResult;
    }
}
