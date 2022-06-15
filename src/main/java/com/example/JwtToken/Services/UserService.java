package com.example.JwtToken.Services;

import com.example.JwtToken.Entity.User;

public interface UserService {

    User saveUser(User user);

    User findByUsername(String username);

}
