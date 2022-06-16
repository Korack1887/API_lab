package com.nure.API_lab.Services;

import com.nure.API_lab.entities.Authorization;
import com.nure.API_lab.repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    AuthorizationRepository repository;

    public boolean login(Authorization authorization){
        return repository.findAll().stream().anyMatch(u-> u.login.equals(authorization.login) && u.password.equals(authorization.password));

    }

    public Authorization getByEmail(String email){
        return repository.findAll().stream().filter(u -> u.login.equals(email)).findFirst().get();

    }
}
