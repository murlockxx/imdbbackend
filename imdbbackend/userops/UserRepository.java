package com.imdbv1.imdbbackend.userops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    public boolean existsByEmail(String email);

    public User findByEmail(String email);
    
}
