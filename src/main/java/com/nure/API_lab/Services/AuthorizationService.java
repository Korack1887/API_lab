package com.nure.API_lab.Services;

import com.nure.API_lab.entities.Authorization;
import com.nure.API_lab.repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {
    @Autowired
    AuthorizationRepository repository;

    public boolean login(Authorization authorization){
        return repository.findAll().stream().anyMatch(u-> u.login.equals(authorization.login) && u.password.equals(authorization.password));

    }

    public Authorization getByLogin(String login){
        List<Authorization> list = repository.getByLogin(login);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public Authorization getByEmail(String email){
        return repository.findAll().stream().filter(u -> u.login.equals(email)).findFirst().get();
    }
}
