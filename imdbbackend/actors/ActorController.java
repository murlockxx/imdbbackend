package com.imdbv1.imdbbackend.actors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {

    @Autowired
    ActorService actorService;



    @PostMapping("/add")
    public ResponseEntity<String> addActor(@RequestBody Actor actor) {
        System.out.println(actor.toString());
    
        if(actorService.addMovie(actor)){
            return ResponseEntity.ok("The actor is added");

        }
        else{
            return  ResponseEntity.noContent().build();
        }
        
    }

    @GetMapping("/getAll")
    public List<Actor> getMethodName() {
        return actorService.getAll(); 
    }
    
    @GetMapping("/getDetails/{name}")
    public Actor getMethodName(@PathVariable String name) {
        System.out.println("name of actor in controller" + name);
        String newName=name.replace('-', ' ');
        Actor movie=actorService.getDetails(newName);
        System.out.println(movie.toString());
        return movie;
    }




    
}
