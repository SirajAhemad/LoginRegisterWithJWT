package com.example.JwtToken.Services.Impl;

import com.example.JwtToken.Entity.User;
import com.example.JwtToken.Repository.UserRepo;
import com.example.JwtToken.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
