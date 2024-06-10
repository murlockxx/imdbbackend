package com.imdbv1.imdbbackend.actors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Long>{

    Actor findByName(String newName);

    List<Actor> findByNameContaining(String query);
    
}
