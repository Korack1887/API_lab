package com.nure.API_lab.Services;

import com.nure.API_lab.entities.User;
import com.nure.API_lab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public boolean login(User user){
        return repository.findAll().stream().anyMatch(u-> u.email.equals(user.email) && u.password.equals(user.password));
    }

    public User getUserByEmail(String email){
        return repository.findAll().stream().filter(u -> u.email.equals(email)).findFirst().get();
    }
}
