package com.t3.visitoraccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.t3.visitoraccess.entity.User;
import com.t3.visitoraccess.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void createUser(User user){
        user.setRoles("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
