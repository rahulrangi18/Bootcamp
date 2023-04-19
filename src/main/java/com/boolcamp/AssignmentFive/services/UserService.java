package com.boolcamp.AssignmentFive.services;

import com.boolcamp.AssignmentFive.models.User;
import com.boolcamp.AssignmentFive.repositories.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMongoRepository userMongoRepository;
    public String setPassLogin(String email, String password) {
        User existingUser = userMongoRepository.findByEmail(email);
        if (existingUser != null) {
            return "User already exists";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userMongoRepository.save(user);
        return "Signup Successful";
    }

    public String passLogin(String email, String password) {
        User user = userMongoRepository.findByEmail(email);
        if (user == null) {
            return "User does not exist";
        }
        if (!user.getPassword().equals(password)) {
            return "Incorrect password";
        }
        String token = generateToken();
        userMongoRepository.delete(user);
        user.setToken(token);
        userMongoRepository.save(user);
        return "Login Successful with token: "+ token;
    }

    public String updatePass(String email, String password, String newPass) {
        User user = userMongoRepository.findByEmail(email);
        if (user == null) {
            return "User does not exist";
        }
        if (!user.getPassword().equals(password)) {
            return "Incorrect password";
        }
        userMongoRepository.delete(user);
        user.setPassword(newPass);
        userMongoRepository.save(user);
        return "Password Updated";
    }
    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
