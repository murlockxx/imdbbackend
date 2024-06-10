package com.imdbv1.imdbbackend.userops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "User already exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added";
    }

    public boolean authenticate(UserCredentials userCredentials) {
        User user = userRepository.findByEmail(userCredentials.getEmail());
        return user != null && passwordEncoder.matches(userCredentials.getPassword(), user.getPassword());
    }

    public User findByEmail(String email) {
        User user=
        userRepository.findByEmail(email);
        return user;
    }
}


