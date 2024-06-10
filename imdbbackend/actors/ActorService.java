package com.imdbv1.imdbbackend.actors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public boolean addMovie(Actor actor) {
        if(!actorRepository.existsById(actor.getId())){
            actorRepository.save(actor);
            return true;
        }
        else{
            return false;
        }
        
    }

    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    public Actor getDetails(String newName) {
        return actorRepository.findByName(newName);
    }
    
}
