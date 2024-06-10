package com.imdbv1.imdbbackend.movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("api/v1/movie")
public class MovieController {


    @Autowired
    MovieService movieService;


    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {

        System.out.println(movie.toString());
    
        if(movieService.addMovie(movie)){
            return ResponseEntity.ok("The movie is added");

        }
        else{
            return  ResponseEntity.noContent().build();
        }
        
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    

    @GetMapping("/getDetails/{name}")
    public Movie getMethodName(@PathVariable String name) {
        System.out.println("name of movie in controller" + name);
        String newName=name.replace('-', ' ');
        Movie movie=movieService.getDetails(newName);
        System.out.println(movie.toString());
        return movie;
    }
    



    
}
